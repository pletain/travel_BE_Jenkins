package com.samsam.travel.travelcommerce.dto.review;

import com.samsam.travel.travelcommerce.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewResponseDto {
    private String reviewId;
    private String userId;
    private String ordersId;
    private String ticketId;
    private String comment;
    private float rating;
    private String deleteYn;
    private LocalDateTime registDate;

    public void setReviewAddData(ReviewAddDto reviewAddDto) {
        this.comment = reviewAddDto.getComment();
        this.rating = reviewAddDto.getRating();
    }

    public static ReviewResponseDto convertEntityToDto(Review review) {
        return new ReviewResponseDto(
            review.getReviewId(),
            review.getUser().getUserId(),
            review.getOrders().getOrderId(),
            review.getTicket().getTicketId(),
            review.getComment(),
            review.getRating(),
            review.getDeleteYn(),
            review.getRegistDate()
        );
    }
}
