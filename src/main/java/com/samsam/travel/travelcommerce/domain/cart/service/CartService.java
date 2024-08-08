package com.samsam.travel.travelcommerce.domain.cart.service;

import com.samsam.travel.travelcommerce.dto.cart.CartDto;
import com.samsam.travel.travelcommerce.dto.ticket.TicketDto;

import java.util.List;

public interface CartService {


    public List<TicketDto> getMyCartTicket(CartDto cartDto);
}
