package com.samsam.travel.travelcommerce.domain.ticket.service;

import com.samsam.travel.travelcommerce.dto.ticket.TicketDto;
import com.samsam.travel.travelcommerce.dto.ticket.TicketResponseDto;

public interface TicketService {

    public TicketResponseDto addTicket(TicketDto ticketDto);

    public int updateTicket(TicketDto ticketDto);
}
