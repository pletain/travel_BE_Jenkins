package com.samsam.travel.travelcommerce.domain.cart.service;

import com.samsam.travel.travelcommerce.domain.cart.repository.CartRepository;
import com.samsam.travel.travelcommerce.dto.cart.CartDto;
import com.samsam.travel.travelcommerce.entity.Cart;
import com.samsam.travel.travelcommerce.utils.Common;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository repository;

    private final Common common;

    @Override
    public List<CartDto> getMyCartTicket(CartDto cartDto) {
        return repository.findMyCart(cartDto).stream().map(CartDto::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public CartDto addMyCart(CartDto cartDto) {
        Cart cart = repository.findMyCartTicket(cartDto);

        if(cart != null) {
            cartDto.setCartId(cart.getCartId());
            repository.updateCart(new Cart().convertDtoToEntity(cartDto));
            return new CartDto().convertUpdateEntityToDto(cart, cartDto.getQuantity());
        }

        String cartId = common.getTargetUuid("cart");
        cartDto.setCartId(cartId);
        return new CartDto().convertEntityToDto(repository.save(new Cart().convertDtoToEntity(cartDto)));
    }

    @Override
    public boolean deleteMyCart(String cartId) {
        return repository.deleteCart(cartId) > 0;
    }
}
