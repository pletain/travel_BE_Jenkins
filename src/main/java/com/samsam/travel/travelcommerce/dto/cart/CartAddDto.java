package com.samsam.travel.travelcommerce.dto.cart;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartAddDto {
    private String ticketId;
    private int quantity;

    public Boolean isValidate() {
        return !(
            StringUtils.isNotBlank(ticketId)
            && quantity > 0
        );
    }
}
