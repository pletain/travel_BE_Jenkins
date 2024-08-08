package com.samsam.travel.travelcommerce.domain.cart.service;

import com.samsam.travel.travelcommerce.domain.cart.repository.CartRepository;
import com.samsam.travel.travelcommerce.dto.cart.CartDto;
import com.samsam.travel.travelcommerce.dto.ticket.TicketDto;
import com.samsam.travel.travelcommerce.dto.ticket.TicketResponseDto;
import com.samsam.travel.travelcommerce.entity.Ticket;
import com.samsam.travel.travelcommerce.utils.Common;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository repository;

    private final Common common;

    @Override
    public List<TicketDto> getMyCartTicket(CartDto cartDto) {
        return repository.findMyCart(cartDto).stream().map(TicketDto::convertEntityToDto).collect(Collectors.toList());
    }
}
