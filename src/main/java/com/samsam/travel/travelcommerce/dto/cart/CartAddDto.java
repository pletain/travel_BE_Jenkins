package com.samsam.travel.travelcommerce.dto.cart;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CartAddDto {
    private String ticketId;
    private int quantity;
}
