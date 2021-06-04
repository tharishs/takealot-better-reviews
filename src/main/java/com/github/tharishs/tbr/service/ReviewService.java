package com.github.tharishs.tbr.service;

import com.github.tharishs.tbr.exception.IntegrationException;
import com.github.tharishs.tbr.exception.ServiceException;
import com.github.tharishs.tbr.model.review.ReviewResponse;
import com.github.tharishs.tbr.util.lucene.Keyword;

import java.util.List;

public interface ReviewService {


    /**
     * Gets all the Takealot reviews for a valid PLID (Takealot product ID)
     *
     * @param plid Takealot product ID
     * @return a list of all reviews for a specific PLID
     * @throws IntegrationException if failed to look up reviews using Takealot API
     */
    ReviewResponse getReviews(String plid) throws IntegrationException;

    /**
     * Gets all the Takealot reviews filtered by star rating for a valid PLID (Takealot product ID)
     *
     * @param plid       Takealot product ID
     * @param starRating takealot star rating [1 - 5]
     * @return a list of reviews filtered by star rating for a specific PLID
     * @throws ServiceException     if rating is invalid
     * @throws IntegrationException if failed to look up reviews using Takealot API
     */
    ReviewResponse filterReview(String plid, int starRating) throws ServiceException, IntegrationException;

    /**
     * Tries to find the relevant keywords for reviews of a PLID
     *
     * @param plid Takealot product ID
     * @return a list of keywords based for reviews of a certain PLID
     * @throws IntegrationException if failed to look up reviews using Takealot API
     */
    List<Keyword> findAllKeywords(String plid) throws IntegrationException;

    ReviewResponse searchByKeyword(String plid, String searchTerm) throws ServiceException, IntegrationException;


}
