package com.samsam.travel.travelcommerce.domain.review.service;

import com.samsam.travel.travelcommerce.domain.review.repository.ReviewRepository;
import com.samsam.travel.travelcommerce.dto.review.ReviewDto;
import com.samsam.travel.travelcommerce.entity.Review;
import com.samsam.travel.travelcommerce.utils.Common;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repository;

    private final Common common;

    @Override
    public ReviewDto addReview(ReviewDto reviewDto) {
        String reviewId = common.getTargetUuid("review");
        reviewDto.setReviewId(reviewId);

        return new ReviewDto().convertEntityToDto(repository.save(Review.convertDtoToEntity(reviewDto)));
    }

    @Override
    public ReviewDto getMyOrderReview(ReviewDto reviewDto) {
        return new ReviewDto().convertEntityToDto(repository.findMyReview(Review.convertDtoToEntity(reviewDto)));
    }
}
