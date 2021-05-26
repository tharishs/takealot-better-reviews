package com.github.tharishs.tbr.controller;

import com.github.tharishs.tbr.exception.IntegrationException;
import com.github.tharishs.tbr.exception.ServiceException;
import com.github.tharishs.tbr.model.review.ReviewResponse;
import com.github.tharishs.tbr.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "api/review/v1", produces = MediaType.APPLICATION_JSON_VALUE)
class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @GetMapping(value = "/get/reviews", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Retrieves all reviews from a given takealot product url that includes a PLID")
    public ResponseEntity<ReviewResponse> getReviews(@RequestParam String takealotUrl) throws ServiceException, IntegrationException {

        return ResponseEntity.ok(reviewService.getReviews(takealotUrl));
    }
}
