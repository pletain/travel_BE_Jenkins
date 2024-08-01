package com.samsam.travel.travelcommerce.dao;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

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
    @NotNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    @NotNull
    private Ticket ticket;

    @Column(name = "order_date", nullable = false, updatable = false)
    @NotNull
    private LocalDateTime orderDate;

    @Column(name = "total_amount", nullable = false)
    @NotNull
    @Min(1)
    private int totalAmount;

    @Column(name = "quantity", nullable = false)
    @NotNull
    @Min(1)
    private int quantity;

    @Column(name = "status", nullable = false, length = 1)
    @NotNull
    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(orderId, orders.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }
}