package com.samsam.travel.travelcommerce.dto.review;

import com.samsam.travel.travelcommerce.entity.Orders;
import com.samsam.travel.travelcommerce.entity.Review;
import com.samsam.travel.travelcommerce.entity.Ticket;
import com.samsam.travel.travelcommerce.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewDto {
    private String reviewId;
    private User user;
    private Orders orders;
    private Ticket ticket;
    private String comment;
    private float rating;
    private String deleteYn;
    private LocalDateTime registDate;

    public void setReviewAddData(ReviewAddDto reviewAddDto) {
        this.comment = reviewAddDto.getComment();
        this.rating = reviewAddDto.getRating();
    }

    public ReviewDto convertEntityToDto(Review review) {
        return new ReviewDto(
            review.getReviewId(),
            review.getUser(),
            review.getOrders(),
            review.getTicket(),
            review.getComment(),
            review.getRating(),
            review.getDeleteYn(),
            review.getRegistDate()
        );
    }
}
