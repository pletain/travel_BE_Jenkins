package com.samsam.travel.travelcommerce.entity;

import com.samsam.travel.travelcommerce.dto.cart.CartDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
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
    private String deleteYn;

    @CreationTimestamp
    @Column(name = "regist_date", nullable = false, updatable = false)
    private LocalDateTime registDate;

    @UpdateTimestamp
    @Column(name = "update_date", nullable = false)
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

    public Cart convertDtoToEntity(CartDto cartDto) {
        return new Cart(
            cartDto.getCartId(),
            cartDto.getUser(),
            cartDto.getTicket(),
            cartDto.getQuantity(),
            cartDto.getDeleteYn(),
            cartDto.getRegistDate(),
            cartDto.getUpdateDate()
        );
    }
}