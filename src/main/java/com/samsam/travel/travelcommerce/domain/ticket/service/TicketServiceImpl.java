package com.samsam.travel.travelcommerce.domain.ticket.service;

import com.samsam.travel.travelcommerce.domain.ticket.repository.TicketRepository;
import com.samsam.travel.travelcommerce.dto.ticket.SearchDto;
import com.samsam.travel.travelcommerce.dto.ticket.TicketDto;
import com.samsam.travel.travelcommerce.dto.ticket.TicketResponseDto;
import com.samsam.travel.travelcommerce.dto.ticket.TicketSearchResponseDto;
import com.samsam.travel.travelcommerce.entity.Ticket;
import com.samsam.travel.travelcommerce.entity.User;
import com.samsam.travel.travelcommerce.utils.Common;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository repository;

    private final Common common;

    @Override
    public List<TicketSearchResponseDto> getAllTicket(SearchDto searchDto) {
        Pageable pageable = PageRequest.of(searchDto.getPageNumber() - 1, searchDto.getPageSize());
        List<Object[]> result = repository.findAll(searchDto.getKeyword(), pageable);
        return result.stream()
                .map(TicketSearchResponseDto::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TicketDto getTicketDetail(String ticketId) {
        Ticket ticket = repository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id " + ticketId));
        return TicketDto.convertEntityToTicketDto(ticket);
    }

    @Override
    public TicketResponseDto addTicket(TicketDto ticketDto) {
        String ticketId = common.getTargetUuid("ticket");
        ticketDto.setTicketId(ticketId);

        return new TicketResponseDto().convertEntityToDto(repository.save(Ticket.convertDtoToEntity(ticketDto)));
    }

    @Override
    public int updateTicket(TicketDto ticketDto) {
        return repository.updateTicket(Ticket.convertDtoToEntity(ticketDto));
    }

    @Override
    public void removeTicket(TicketDto ticketDto) {
        repository.delete(Ticket.convertDtoToEntity(ticketDto));
    }
}
