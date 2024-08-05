package com.samsam.travel.travelcommerce.global.error.exception;

import com.samsam.travel.travelcommerce.global.status.ErrorCode;
import lombok.Getter;

@Getter
public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
