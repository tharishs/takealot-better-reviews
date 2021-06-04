package com.github.tharishs.tbr.service.impl;

import com.github.tharishs.tbr.constant.ErrorEnum;
import com.github.tharishs.tbr.exception.IntegrationException;
import com.github.tharishs.tbr.exception.ServiceException;
import com.github.tharishs.tbr.integration.TakealotApiClient;
import com.github.tharishs.tbr.model.review.Item;
import com.github.tharishs.tbr.model.review.ReviewResponse;
import com.github.tharishs.tbr.service.ReviewService;
import com.github.tharishs.tbr.util.StringUtil;
import com.github.tharishs.tbr.util.lucene.Keyword;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tharish Sooruth
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    private final TakealotApiClient takealotApiClient;

    @Autowired
    public ReviewServiceImpl(TakealotApiClient takealotApiClient) {
        this.takealotApiClient = takealotApiClient;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public ReviewResponse getReviews(String plid) throws IntegrationException {
        return takealotApiClient.getReviews(plid);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReviewResponse filterReview(String plid, int starRating) throws ServiceException, IntegrationException {

        if (!(starRating >= 1 && starRating <= 5)) {
            throw new ServiceException(ErrorEnum.INVALID_STAR, starRating + "");
        }

        ReviewResponse allReviews = SerializationUtils.clone(getReviews(plid));
        allReviews.setItems(allReviews.getItems()
                .stream()
                .filter(item -> item.getStarRating() == starRating)
                .collect(Collectors.toList()));
        allReviews.setCount(allReviews.getItems().size());
        allReviews.setStarRating(starRating);
        return allReviews;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Keyword> findAllKeywords(String plid) throws IntegrationException {

        final List<Item> reviewsList = getReviews(plid).getItems();
        final String combinedReviewText = reviewsList.stream()
                .map(t -> t.getReview())
                .collect(Collectors.joining());

        return StringUtil.findKeywords(combinedReviewText);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReviewResponse searchByKeyword(String plid, String searchTerm) throws ServiceException, IntegrationException {

        ReviewResponse reviews = SerializationUtils.clone(getReviews(plid));

        List<Item> items = reviews.getItems().stream()
                .filter(i -> i.getReview().toLowerCase().contains(searchTerm.trim().toLowerCase()))
                .collect(Collectors.toList());

        reviews.setItems(items);
        reviews.setCount(items.size());
        reviews.setStarRating(reCalculateStarRating(items));

        if (items.isEmpty()) {
            throw new ServiceException(ErrorEnum.NO_SEARCH_RESULTS, "Search term: " + searchTerm);
        }

        return reviews;
    }

    private double reCalculateStarRating(List<Item> items) {
        int totalStarSum = items.stream().mapToInt(item -> item.getStarRating()).sum();
        double newStarRating = (double) totalStarSum / (double) items.size();
        return new BigDecimal(newStarRating).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
