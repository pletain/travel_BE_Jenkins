package com.samsam.travel.travelcommerce.dto.ticket;

import com.samsam.travel.travelcommerce.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TicketResponseDto {
    private String ticketId;
    private String userId;
    private String userName;
    private String userPhone;
    private String title;
    private String contents;
    private String place;
    private int price;
    private LocalDate startDate;
    private LocalDate endDate;
    private String deleteYn;
    private String viewYn;
    private LocalDateTime registDate;
    private LocalDateTime updateDate;

    public TicketResponseDto convertEntityToDto(Ticket ticket) {
        return new TicketResponseDto(
                ticket.getTicketId(),
                ticket.getUser().getUserId(),
                ticket.getUser().getName(),
                ticket.getUser().getPhone(),
                ticket.getTitle(),
                ticket.getContents(),
                ticket.getPlace(),
                ticket.getPrice(),
                ticket.getStartDate(),
                ticket.getEndDate(),
                ticket.getDeleteYn(),
                ticket.getViewYn(),
                ticket.getRegistDate(),
                ticket.getUpdateDate()
        );
    }
}
