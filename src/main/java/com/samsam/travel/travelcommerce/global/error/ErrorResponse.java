package com.samsam.travel.travelcommerce.global.error;

import com.samsam.travel.travelcommerce.global.status.ErrorCode;
import lombok.Getter;

/**
 * 이 클래스는 애플리케이션에서 반환할 수 있는 오류 응답을 나타냅니다.
 * 오류를 고유하게 식별하기 위해 메시지와 코드를 포함합니다.
 */
@Getter
public class ErrorResponse {

    //오류 메시지
    private final String message;

    //오류 코드
    private final String code;

    /**
     * ErrorCode를 사용하여 ErrorResponse 객체를 생성합니다.
     *
     * @param code ErrorResponse를 구성하는 ErrorCode.
     *             ErrorResponse의 메시지와 코드는 ErrorCode의 메시지와 이름으로 설정됩니다.
     */
    public ErrorResponse(ErrorCode code) {
        this.message = code.getMessage();
        this.code = code.name();
    }

    /**
     * 사용자 정의 메시지와 코드를 사용하여 ErrorResponse 객체를 생성합니다.
     *
     * @param message 사용자 정의 오류 메시지.
     * @param code    사용자 정의 오류 코드.
     */
    public ErrorResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }

}