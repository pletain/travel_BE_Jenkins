package com.samsam.travel.travelcommerce.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Orders {

    @Id
    @Column(name = "order_id", length = 255)
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;

    @Column(name = "order_date", nullable = false, updatable = false)
    private LocalDateTime orderDate;

    @Column(name = "total_amount", nullable = false)
    private int totalAmount;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "status", nullable = false, length = 1)
    @ColumnDefault("P")
    private String status;

    @PrePersist
    protected void onCreate() {
        this.orderDate = LocalDateTime.now();
        if (this.status == null) {
            this.status = "P"; // 기본 상태값 설정
        }
    }
}