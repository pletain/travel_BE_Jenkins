package com.samsam.travel.travelcommerce.domain.review.repository;

import com.samsam.travel.travelcommerce.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, String> {

    @Query(
        "SELECT " +
            "r " +
        "FROM Review r " +
        "WHERE r.orders.orderId     =   :#{#review.orders.orderId} " +
            "AND r.user.userId      =   :#{#review.user.userId} " +
            "AND r.ticket.ticketId  =   :#{#review.ticket.ticketId} " +
            "AND r.deleteYn         =   'N' "
    )
    Review findMyReview(Review review);

    @Query(
        "SELECT " +
            "r " +
        "FROM Review r " +
        "WHERE r.user.userId      =   :#{#review.user.userId} " +
            "AND r.deleteYn         =   'N' " +
        "ORDER BY r.registDate DESC "
    )
    List<Review> findMyAllReview(Review review);

    @Query(
        "SELECT " +
            "r " +
        "FROM Review r " +
        "WHERE r.ticket.ticketId      =   :#{#ticketId} " +
            "AND r.deleteYn         =   'N' " +
        "ORDER BY r.registDate DESC "
    )
    List<Review> findAllReviewByTicket(String ticketId);

    @Modifying
    @Transactional
    @Query(
        "UPDATE Review r " +
        "SET " +
            "r.deleteYn         =   'Y' " +
        "WHERE r.reviewId       =   :#{#review.reviewId} " +
            "AND r.user.userId  =   :#{#review.user.userId} "
    )
    int deleteMyReview(Review review);

}
