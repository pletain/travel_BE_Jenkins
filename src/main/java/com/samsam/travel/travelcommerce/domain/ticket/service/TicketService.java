package com.samsam.travel.travelcommerce.domain.ticket.service;

import com.samsam.travel.travelcommerce.dto.ticket.TicketAddDto;
import com.samsam.travel.travelcommerce.dto.ticket.TicketModifyDto;

public interface TicketService {

    public Object addTicket(TicketAddDto ticketAddDto);

    public Object updateTicket(TicketModifyDto ticketModifyDto);
}
