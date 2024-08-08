package com.samsam.travel.travelcommerce.dto.ticket;

import com.samsam.travel.travelcommerce.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class TicketSearchResponseDto {
    private String ticketId;
    private User user;
    private String title;
    private String contents;
    private String place;
    private int price;
    private LocalDate startDate;
    private LocalDate endDate;
    private String deleteYn;
    private LocalDateTime registDate;
    private LocalDateTime updateDate;
    private double avgRating;

    public TicketSearchResponseDto(
        String ticketId,
        User user,
        String title,
        String place,
        int price,
        LocalDate startDate,
        LocalDate endDate,
        String deleteYn,
        LocalDateTime registDate,
        LocalDateTime updateDate,
        double avgRating
    ) {
        this.ticketId = ticketId;
        this.user = user;
        this.title = title;
        this.place = place;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deleteYn = deleteYn;
        this.registDate = registDate;
        this.updateDate = updateDate;
        this.avgRating = avgRating;
    }

    public static TicketSearchResponseDto convertToDto(Object[] ticketInfo) {
        return new TicketSearchResponseDto(
                (String) ticketInfo[0],
                (User) ticketInfo[1],
                (String) ticketInfo[2],
                (String) ticketInfo[3],
                (int) ticketInfo[4],
                (LocalDate) ticketInfo[5],
                (LocalDate) ticketInfo[6],
                (String) ticketInfo[7],
                (LocalDateTime) ticketInfo[8],
                (LocalDateTime) ticketInfo[9],
                (Double) ticketInfo[10]
        );
    }
}
