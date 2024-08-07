package com.samsam.travel.travelcommerce.domain.ticket.api;

import com.samsam.travel.travelcommerce.domain.ticket.service.TicketService;
import com.samsam.travel.travelcommerce.dto.ticket.TicketDto;
import com.samsam.travel.travelcommerce.dto.ticket.TicketResponseDto;
import com.samsam.travel.travelcommerce.entity.User;
import com.samsam.travel.travelcommerce.global.status.CommonCode;
import com.samsam.travel.travelcommerce.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import static com.samsam.travel.travelcommerce.global.status.CommonCode.SUCCESS_ADD_TICKET;
import static com.samsam.travel.travelcommerce.global.status.CommonCode.SUCCESS_MODIFY_TICKET;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/regist")
    public ResponseEntity<ApiResponse<TicketResponseDto>> addTicket(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute TicketDto ticketDto) {
        setTicketDto(userDetails, ticketDto);
        TicketResponseDto responseDto = ticketService.addTicket(ticketDto);
        return buildResponseEntity(SUCCESS_ADD_TICKET, responseDto);
    }

    @PutMapping("/modify")
    public ResponseEntity<ApiResponse<Boolean>> updateTicket(@AuthenticationPrincipal UserDetails userDetails, @ModelAttribute TicketDto ticketDto) {
        setTicketDto(userDetails, ticketDto);
        boolean updateYn = ticketService.updateTicket(ticketDto) > 0;
        return buildResponseEntity(SUCCESS_MODIFY_TICKET, updateYn);
    }

    private <T> ResponseEntity<ApiResponse<T>> buildResponseEntity(CommonCode code, T data) {
        ApiResponse<T> response = ApiResponse.createResponse(code, data);
        return ResponseEntity.status(code.getHttpStatus()).body(response);
    }

    public TicketDto setTicketDto(UserDetails userDetails, TicketDto ticketDto) {
        ticketDto.setUser(getUser(userDetails));
        return ticketDto;
    }

    public User getUser(UserDetails userDetails) {
        return  User
                .builder()
                .userId(userDetails.getUsername())
                .password(userDetails.getPassword())
                .build();
    }
}