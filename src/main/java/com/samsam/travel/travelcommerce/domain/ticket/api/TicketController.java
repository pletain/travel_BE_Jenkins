package com.samsam.travel.travelcommerce.domain.ticket.api;

import com.samsam.travel.travelcommerce.domain.ticket.service.TicketService;
import com.samsam.travel.travelcommerce.dto.TicketAddDto;
import com.samsam.travel.travelcommerce.utils.ReturnMessage;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Resource
    TicketService ticketService;

    @PostMapping("/regist")
    public ReturnMessage addTicket(@ModelAttribute TicketAddDto ticketAddDto) {
        return new ReturnMessage(ticketService.addTicket(ticketAddDto));
    }
}
