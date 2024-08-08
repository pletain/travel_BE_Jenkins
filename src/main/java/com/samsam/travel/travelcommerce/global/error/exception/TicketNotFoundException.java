package com.samsam.travel.travelcommerce.global.error.exception;

import com.samsam.travel.travelcommerce.global.status.ErrorCode;
import lombok.Getter;

@Getter
public class TicketNotFoundException extends BusinessException {
    public TicketNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
