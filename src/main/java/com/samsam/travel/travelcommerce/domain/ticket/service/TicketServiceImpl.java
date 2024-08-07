package com.samsam.travel.travelcommerce.domain.ticket.service;

import com.samsam.travel.travelcommerce.domain.ticket.repository.TicketRepository;
import com.samsam.travel.travelcommerce.dto.ticket.TicketDto;
import com.samsam.travel.travelcommerce.dto.ticket.TicketResponseDto;
import com.samsam.travel.travelcommerce.entity.Ticket;
import com.samsam.travel.travelcommerce.entity.User;
import com.samsam.travel.travelcommerce.utils.Common;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository repository;

    private final Common common;

    @Override
    public TicketResponseDto addTicket(TicketDto ticketDto) {
        String ticketId = common.getTargetUuid("ticket");


        ticketDto.setTicketIdAndUser(ticketId, getUserTestId());
        return new TicketResponseDto().convertEntityToDto(repository.save(Ticket.convertDtoToEntity(ticketDto)));
    }

    @Override
    public int updateTicket(TicketDto ticketDto) {
        ticketDto.setUser(getUserTestId());
        return repository.updateTicket(Ticket.convertDtoToEntity(ticketDto));
    }

    public User getUserTestId() {
        return  User
                .builder()
                .userId("test")
                .build();
    }

}
