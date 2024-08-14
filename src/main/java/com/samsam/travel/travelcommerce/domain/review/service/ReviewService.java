package com.samsam.travel.travelcommerce.domain.review.service;

import com.samsam.travel.travelcommerce.dto.review.ReviewDto;
import com.samsam.travel.travelcommerce.dto.review.ReviewResponseDto;

import java.util.List;

public interface ReviewService {

    public ReviewResponseDto addReview(ReviewDto reviewDto);

    public boolean removeReview(ReviewDto reviewDto);

    public ReviewResponseDto getMyOrderReview(ReviewDto reviewDto);

    public List<ReviewResponseDto> getMyAllReview(ReviewDto reviewDto);

    public List<ReviewResponseDto> getAllReviewByTicket(String ticketId);

}
