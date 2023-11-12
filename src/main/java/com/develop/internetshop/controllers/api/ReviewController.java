package com.develop.internetshop.controllers.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.develop.internetshop.entities.Review.Review;
import com.develop.internetshop.entities.Review.ReviewRepository;

/**
* ReviewController
*/
@RestController
@RequestMapping(path = "api/v1/review")
public class ReviewController extends BaseApiController<Review, Long> {
    public ReviewController(ReviewRepository reviewRepository) {
        super(reviewRepository);
    }
}

