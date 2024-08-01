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
@Table(name = "cart")
public class Cart {

    @Id
    @Column(name = "cart_id", length = 255)
    private String cartId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    @NotNull
    private Ticket ticket;

    @Column(name = "quantity", nullable = false)
    @NotNull
    @Min(1)
    private int quantity;

    @Column(name = "delete_yn", nullable = false, length = 1)
    @NotNull
    private String deleteYn;

    @Column(name = "regist_date", nullable = false, updatable = false)
    @NotNull
    private LocalDateTime registDate;

    @Column(name = "update_date", nullable = false)
    @NotNull
    private LocalDateTime updateDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(cartId, cart.cartId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId);
    }
}