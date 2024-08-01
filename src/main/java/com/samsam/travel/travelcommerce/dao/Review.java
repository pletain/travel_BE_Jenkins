package com.samsam.travel.travelcommerce.dao;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "review")
public class Review {

    @Id
    @Column(name = "review_id", length = 255)
    private String reviewId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "rating", nullable = false)
    private float rating;

    @Column(name = "regist_date", nullable = false, updatable = false)
    private LocalDateTime registDate;

    @PrePersist
    protected void onCreate() {
        this.registDate = LocalDateTime.now();
    }
}