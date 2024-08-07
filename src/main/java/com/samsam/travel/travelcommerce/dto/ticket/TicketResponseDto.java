package com.samsam.travel.travelcommerce.dto.ticket;

import com.samsam.travel.travelcommerce.entity.Ticket;
import com.samsam.travel.travelcommerce.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class TicketResponseDto {
    private String ticketId;
    private User user;
    private String title;
    private String contents;
    private String place;
    private int price;
    private LocalDate startDate;
    private LocalDate endDate;
    private String deleteYn;
    private String updateYn;
    private LocalDateTime registDate;
    private LocalDateTime updateDate;

    public TicketResponseDto(
        String ticketId,
        User user,
        String title,
        String contents,
        String place,
        int price,
        LocalDate startDate,
        LocalDate endDate,
        String deleteYn,
        String updateYn,
        LocalDateTime registDate,
        LocalDateTime updateDate
    ) {
        this.ticketId = ticketId;
        this.user = user;
        this.title = title;
        this.contents = contents;
        this.place = place;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deleteYn = deleteYn;
        this.updateYn = updateYn;
        this.registDate = registDate;
        this.updateDate = updateDate;
    }

    public TicketResponseDto convertEntityToDto(Ticket ticket) {
        return new TicketResponseDto(
                ticket.getTicketId(),
                ticket.getUser(),
                ticket.getTitle(),
                ticket.getContents(),
                ticket.getPlace(),
                ticket.getPrice(),
                ticket.getStartDate(),
                ticket.getEndDate(),
                ticket.getDeleteYn(),
                ticket.getVeiwYn(),
                ticket.getRegistDate(),
                ticket.getUpdateDate()
        );
    }
}
