package com.samsam.travel.travelcommerce.dto.review;

import com.samsam.travel.travelcommerce.entity.Orders;
import com.samsam.travel.travelcommerce.entity.Ticket;
import com.samsam.travel.travelcommerce.entity.User;
import io.micrometer.common.util.StringUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class ReviewAddDto {
    private String orderId;
    private String ticketId;
    private String comment;
    private float rating;

    public boolean isValidate() {
        return !(
            StringUtils.isNotBlank(orderId)
            && StringUtils.isNotBlank(ticketId)
            && StringUtils.isNotBlank(comment)
            && rating > 0
        );
    }
}
