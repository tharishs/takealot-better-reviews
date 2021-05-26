package com.github.tharishs.tbr.service;

import com.github.tharishs.tbr.exception.IntegrationException;
import com.github.tharishs.tbr.exception.ServiceException;
import com.github.tharishs.tbr.model.review.ReviewResponse;

public interface ReviewService {


    ReviewResponse getReviews(String url) throws ServiceException, IntegrationException;

}
