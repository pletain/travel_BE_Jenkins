package com.samsam.travel.travelcommerce.global.error.exception;


import com.samsam.travel.travelcommerce.global.status.ErrorCode;

public class UserLoginException extends BusinessException {
    public UserLoginException(ErrorCode errorCode) {
        super(errorCode);
    }
}
