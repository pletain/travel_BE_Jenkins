package com.samsam.travel.travelcommerce.domain.review.service;

import com.samsam.travel.travelcommerce.dto.review.ReviewDto;

public interface ReviewService {

    public ReviewDto addReview(ReviewDto reviewDto);

    public boolean removeReview(ReviewDto reviewDto);

    public ReviewDto getMyOrderReview(ReviewDto reviewDto);

}
