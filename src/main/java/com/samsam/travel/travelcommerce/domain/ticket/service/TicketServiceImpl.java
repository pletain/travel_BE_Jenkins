package com.samsam.travel.travelcommerce.domain.ticket.service;

import com.samsam.travel.travelcommerce.dao.Ticket;
import com.samsam.travel.travelcommerce.dao.User;
import com.samsam.travel.travelcommerce.domain.ticket.repository.TicketRepository;
import com.samsam.travel.travelcommerce.dto.TicketAddDto;
import com.samsam.travel.travelcommerce.utils.Common;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    @Resource
    TicketRepository repository;

    @Resource
    Common common;

    public Object addTicket(TicketAddDto ticketAddDto) {
        String ticketId = common.getTargetUuid("ticket");

        User user = new User()
                .builder()
                .userId("test")
                .build();

        Ticket ticket = new Ticket().builder()
                .ticketId(ticketId)
                .user(user)
                .title(ticketAddDto.getTitle())
                .contents(ticketAddDto.getContents())
                .place(ticketAddDto.getPlace())
                .price(ticketAddDto.getPrice())
                .startDate(ticketAddDto.getStartDate())
                .endDate(ticketAddDto.getEndDate())
                .build();

        return repository.save(ticket);
    }

}
