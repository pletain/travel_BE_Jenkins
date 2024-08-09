package com.samsam.travel.travelcommerce.domain.cart.service;

import com.samsam.travel.travelcommerce.dto.cart.CartDto;
import com.samsam.travel.travelcommerce.dto.cart.CartResponseDto;

import java.util.List;

public interface CartService {

    public List<CartResponseDto> getMyCartTicket(CartDto cartDto);

    public CartResponseDto addMyCart(CartDto cartDto);

    public boolean deleteMyCart(String cartId);
}
