package com.github.tharishs.tbr.integration.impl;

import com.github.tharishs.tbr.config.prop.TakealotEndpointProperties;
import com.github.tharishs.tbr.exception.IntegrationException;
import com.github.tharishs.tbr.integration.RestClientRequestBuilder;
import com.github.tharishs.tbr.integration.RestIntegrationClient;
import com.github.tharishs.tbr.integration.TakealotApiClient;
import com.github.tharishs.tbr.model.review.ReviewResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class TakealotApiClientImpl implements TakealotApiClient {

    private final RestIntegrationClient restIntegrationClient;
    private final TakealotEndpointProperties endpointProperties;

    @Autowired
    public TakealotApiClientImpl(RestIntegrationClient restIntegrationClient, TakealotEndpointProperties endpointProperties) {
        this.restIntegrationClient = restIntegrationClient;
        this.endpointProperties = endpointProperties;
    }

    @Override
    public ReviewResponse getReviews(String plid) throws IntegrationException {

        return restIntegrationClient.consumeEndpoint(
                RestClientRequestBuilder.builder()
                        .baseUrl(endpointProperties.getTakealotBaseUrl())
                        .method(HttpMethod.GET)
                        .uriFunction(uriBuilder -> uriBuilder.path(endpointProperties.getTakealotReviewUriPart()).build(plid))
                        .build(), ReviewResponse.class
        ).getBody();
    }
}
