package com.samsam.travel.travelcommerce.domain.ticket.api;

import com.samsam.travel.travelcommerce.domain.ticket.service.TicketService;
import com.samsam.travel.travelcommerce.dto.ticket.TicketAddDto;
import com.samsam.travel.travelcommerce.dto.ticket.TicketModifyDto;
import com.samsam.travel.travelcommerce.utils.ReturnMessage;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Resource
    TicketService ticketService;

    @PostMapping("/regist")
    public ReturnMessage addTicket(@ModelAttribute TicketAddDto ticketAddDto) {
        return new ReturnMessage(ticketService.addTicket(ticketAddDto));
    }

    @PutMapping("/modify")
    public ReturnMessage updateTicket(@ModelAttribute TicketModifyDto ticketModifyDto) {
        return new ReturnMessage(ticketService.updateTicket(ticketModifyDto));
    }
}