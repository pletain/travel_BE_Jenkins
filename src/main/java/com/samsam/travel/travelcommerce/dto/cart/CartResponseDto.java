package com.samsam.travel.travelcommerce.dto.cart;

import com.samsam.travel.travelcommerce.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartResponseDto {
    private String cartId;
    private String userId;
    private String ticketId;
    private int quantity;
    private String deleteYn;
    private LocalDateTime registDate;
    private LocalDateTime updateDate;

    public static CartResponseDto convertEntityToDto(Cart cart) {
        return new CartResponseDto(
            cart.getCartId(),
            cart.getUser().getUserId(),
            cart.getTicket().getTicketId(),
            cart.getQuantity(),
            cart.getDeleteYn(),
            cart.getRegistDate(),
            cart.getUpdateDate()
        );
    }

    public static CartResponseDto convertUpdateEntityToDto(Cart cart, int quantity) {
        return new CartResponseDto(
            cart.getCartId(),
            cart.getUser().getUserId(),
            cart.getTicket().getTicketId(),
            quantity,
            cart.getDeleteYn(),
            cart.getRegistDate(),
            cart.getUpdateDate()
        );
    }
}
