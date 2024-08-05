package com.samsam.travel.travelcommerce.global.error.exception;

import com.samsam.travel.travelcommerce.global.status.ErrorCode;

public class UserDuplicateException extends BusinessException {
    public UserDuplicateException(ErrorCode errorCode) {
        super(errorCode);
    }
}
