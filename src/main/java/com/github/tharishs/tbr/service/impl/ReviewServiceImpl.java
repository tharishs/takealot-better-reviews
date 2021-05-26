package com.github.tharishs.tbr.service.impl;

import com.github.tharishs.tbr.constant.ErrorEnum;
import com.github.tharishs.tbr.exception.IntegrationException;
import com.github.tharishs.tbr.exception.ServiceException;
import com.github.tharishs.tbr.integration.TakealotApiClient;
import com.github.tharishs.tbr.model.review.ReviewResponse;
import com.github.tharishs.tbr.service.ReviewService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final TakealotApiClient takealotApiClient;

    @Autowired
    public ReviewServiceImpl(TakealotApiClient takealotApiClient) {
        this.takealotApiClient = takealotApiClient;
    }


    private String getPlid(String url) throws ServiceException {
        if (StringUtils.isBlank(url)) {
            throw new ServiceException(ErrorEnum.INVALID_URL, url);
        }

        if (!StringUtils.containsIgnoreCase(url, "PLID")) {
            throw new ServiceException(ErrorEnum.INVALID_URL, url);
        }

        if (!StringUtils.containsIgnoreCase(url, "/")) {
            throw new ServiceException(ErrorEnum.INVALID_URL, url);
        }

        String[] split = url.split("/");

        return split[split.length - 1];
    }

    @Override
    public ReviewResponse getReviews(String url) throws ServiceException, IntegrationException {

        return takealotApiClient.getReviews(getPlid(url));
    }
}
