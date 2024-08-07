package com.samsam.travel.travelcommerce.global.error.exception;

import com.samsam.travel.travelcommerce.global.status.ErrorCode;

/**
 * 사용자가 권한이 없을 때 발생하는 예외 클래스.
 * BusinessException을 확장합니다.
 */
public class UserUnauthorizedException extends BusinessException {

    /**
     * 지정된 오류 코드로 새 UserUnauthorizedException 생성합니다.
     *
     * @param errorCode 이 예외와 관련된 특정 오류 코드.
     */
    public UserUnauthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }
}