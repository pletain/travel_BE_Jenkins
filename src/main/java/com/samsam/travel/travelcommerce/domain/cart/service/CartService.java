package com.samsam.travel.travelcommerce.domain.cart.service;

import com.samsam.travel.travelcommerce.dto.cart.CartDto;

import java.util.List;

public interface CartService {

    public List<CartDto> getMyCartTicket(CartDto cartDto);

    public CartDto addMyCart(CartDto cartDto);

    public boolean deleteMyCart(String cartId);
}
