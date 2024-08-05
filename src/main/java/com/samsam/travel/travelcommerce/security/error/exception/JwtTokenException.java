package com.samsam.travel.travelcommerce.security.error.exception;

import com.samsam.travel.travelcommerce.global.status.ErrorCode;
import io.jsonwebtoken.JwtException;
import lombok.Getter;

/**
 * JWT 토큰 관련 예외를 처리하기 위한 사용자 정의 예외 클래스입니다.
 * io.jsonwebtoken 라이브러리의 JwtException 클래스를 확장합니다.
 *
 * @author lavin
 * @since 1.0
 */
@Getter
public class JwtTokenException extends JwtException {
    /**
     * 에러 코드
     */
    private final ErrorCode errorCode;

    /**
     * JwtTokenException 생성자
     *
     * @param errorCode 에러 코드
     */
    public JwtTokenException(ErrorCode errorCode) {
        super(errorCode.getMessage()); // 에러 메시지로 JwtException을 초기화합니다.
        this.errorCode = errorCode; // 에러 코드를 저장합니다.
    }
}
