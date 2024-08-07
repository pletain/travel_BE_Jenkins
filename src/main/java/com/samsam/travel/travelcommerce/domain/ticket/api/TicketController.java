package com.samsam.travel.travelcommerce.domain.ticket.api;

import com.samsam.travel.travelcommerce.domain.ticket.service.TicketService;
import com.samsam.travel.travelcommerce.dto.ticket.TicketDto;
import com.samsam.travel.travelcommerce.utils.ReturnMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/regist")
    public ReturnMessage addTicket(@ModelAttribute TicketDto ticketDto) {
        return new ReturnMessage(ticketService.addTicket(ticketDto));
    }

    @PutMapping("/modify")
    public ReturnMessage updateTicket(@ModelAttribute TicketDto ticketDto) {
        return new ReturnMessage(ticketService.updateTicket(ticketDto));
    }
}