package com.github.tharishs.tbr.integration;

import com.github.tharishs.tbr.exception.IntegrationException;
import com.github.tharishs.tbr.model.detail.ProductDetails;
import com.github.tharishs.tbr.model.review.ReviewResponse;

public interface TakealotApiClient {

    ReviewResponse getReviews(String plid) throws IntegrationException;

    ProductDetails getProductDetails(String plid) throws IntegrationException;
}
