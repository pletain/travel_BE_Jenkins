package com.samsam.travel.travelcommerce.dto.cart;

import com.samsam.travel.travelcommerce.entity.Ticket;
import com.samsam.travel.travelcommerce.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
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
}
