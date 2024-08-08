package com.samsam.travel.travelcommerce.global.error.exception;

import com.samsam.travel.travelcommerce.global.status.ErrorCode;
import lombok.Getter;

/**
 * [장바구니] 올바르지 않은 값을 입력했을 때 발생하는 에러
 * BusinessException을 확장합니다.
 */
@Getter
public class CartInvalidInputException extends BusinessException {
    /**
     * 지정된 오류 코드로 새 TicketInvalidInputException 생성합니다.
     * @param errorCode 이 예외와 관련된 특정 오류 코드.
     */
    public CartInvalidInputException(ErrorCode errorCode) {
        super(errorCode);
    }
}
