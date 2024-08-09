package com.samsam.travel.travelcommerce.domain.review.repository;

import com.samsam.travel.travelcommerce.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, String> {

    @Query(
        "SELECT " +
            "r " +
        "FROM Review r " +
        "WHERE r.orders.orderId     =   :#{#review.orders.orderId} " +
            "AND r.user.userId      =   :#{#review.user.userId} " +
            "AND r.ticket.ticketId  =   :#{#review.ticket.ticketId}"
    )
    Review findMyReview(Review review);

}
