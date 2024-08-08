package com.samsam.travel.travelcommerce.dto.ticket;

import com.samsam.travel.travelcommerce.entity.Ticket;
import com.samsam.travel.travelcommerce.entity.User;
import io.micrometer.common.util.StringUtils;
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
public class TicketDto {
    private String ticketId;
    private User user;
    private String title;
    private String contents;
    private String place;
    private int price;
    private LocalDate startDate;
    private LocalDate endDate;
    private String deleteYn;
    private String veiwYn;
    private LocalDateTime registDate;
    private LocalDateTime updateDate;

    public void setTicketIdAndUser(String ticketId, User user) {
        this.ticketId = ticketId;
        this.user = user;
    }

    public static TicketDto convertEntityToTicketDto(Ticket ticket) {
        return new TicketDto(
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

    public boolean isValidate() {
        return
            !(
                StringUtils.isNotBlank(title)
                && StringUtils.isNotBlank(contents)
                && StringUtils.isNotBlank(place)
                && price > 0
                && startDate != null
                && endDate != null
            );
    }
}
