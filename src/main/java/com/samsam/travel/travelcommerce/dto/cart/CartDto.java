package com.samsam.travel.travelcommerce.dto.cart;

import com.samsam.travel.travelcommerce.entity.Cart;
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
public class CartDto {
    private String cartId;
    private User user;
    private Ticket ticket;
    private int quantity;
    private String deleteYn;
    private LocalDateTime registDate;
    private LocalDateTime updateDate;

    public static CartDto convertEntityToDto(Cart cart) {
        return new CartDto(
            cart.getCartId(),
            cart.getUser(),
            cart.getTicket(),
            cart.getQuantity(),
            cart.getDeleteYn(),
            cart.getRegistDate(),
            cart.getUpdateDate()
        );
    }
}
