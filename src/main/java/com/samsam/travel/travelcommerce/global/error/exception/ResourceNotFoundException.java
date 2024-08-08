package com.samsam.travel.travelcommerce.global.error.exception;

import com.samsam.travel.travelcommerce.global.status.ErrorCode;
import lombok.Getter;

@Getter
public class ResourceNotFoundException extends BusinessException {
    public ResourceNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
