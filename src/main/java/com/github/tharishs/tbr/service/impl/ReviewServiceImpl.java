package com.github.tharishs.tbr.service.impl;

import com.github.tharishs.tbr.constant.ErrorEnum;
import com.github.tharishs.tbr.exception.IntegrationException;
import com.github.tharishs.tbr.exception.ServiceException;
import com.github.tharishs.tbr.integration.TakealotApiClient;
import com.github.tharishs.tbr.model.review.ReviewResponse;
import com.github.tharishs.tbr.service.ReviewService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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

        ReviewResponse allReviews = takealotApiClient.getReviews(plid);
        allReviews.setItems(allReviews.getItems()
                .stream()
                .filter(item -> item.getStarRating() == starRating)
                .collect(Collectors.toList()));
        return allReviews;
    }
}
