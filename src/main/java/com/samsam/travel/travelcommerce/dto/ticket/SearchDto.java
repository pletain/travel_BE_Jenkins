package com.samsam.travel.travelcommerce.dto.ticket;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SearchDto {
    private String keyword;
    private int pageNumber;
    private int pageSize;

    public boolean isValidate() {
        return
            !(
                pageNumber > 0
                && pageSize > 0
            );
    }
}
