package com.samsam.travel.travelcommerce.dto;

import com.samsam.travel.travelcommerce.dao.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class TicketAddDto {
    private String title;
    private String contents;
    private String place;
    private int price;
    private LocalDate startDate;
    private LocalDate endDate;

    public TicketAddDto(
        String title,
        String contents,
        String place,
        int price,
        LocalDate startDate,
        LocalDate endDate
    ) {
        this.title = title;
        this.contents = contents;
        this.place = place;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
