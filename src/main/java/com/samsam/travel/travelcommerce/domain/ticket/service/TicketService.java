package com.samsam.travel.travelcommerce.domain.ticket.service;

import com.samsam.travel.travelcommerce.dto.ticket.TicketDto;

public interface TicketService {

    public Object addTicket(TicketDto ticketDto);

    public Object updateTicket(TicketDto ticketDto);
}
