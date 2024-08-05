package com.samsam.travel.travelcommerce.domain.ticket.service;

import com.samsam.travel.travelcommerce.domain.ticket.repository.TicketRepository;
import com.samsam.travel.travelcommerce.dto.ticket.TicketAddDto;
import com.samsam.travel.travelcommerce.dto.ticket.TicketModifyDto;
import com.samsam.travel.travelcommerce.entity.Ticket;
import com.samsam.travel.travelcommerce.entity.User;
import com.samsam.travel.travelcommerce.utils.Common;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    @Resource
    TicketRepository repository;

    @Resource
    Common common;

    @Override
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

    @Override
    public Object updateTicket(TicketModifyDto ticketModifyDto) {
        User user = new User()
                .builder()
                .userId("test")
                .build();

        Ticket ticket = new Ticket().builder()
                .ticketId(ticketModifyDto.getTicketId())
                .user(user)
                .title(ticketModifyDto.getTitle())
                .contents(ticketModifyDto.getContents())
                .place(ticketModifyDto.getPlace())
                .price(ticketModifyDto.getPrice())
                .startDate(ticketModifyDto.getStartDate())
                .endDate(ticketModifyDto.getEndDate())
                .build();

        return repository.updateTicket(ticket);
    }

}
