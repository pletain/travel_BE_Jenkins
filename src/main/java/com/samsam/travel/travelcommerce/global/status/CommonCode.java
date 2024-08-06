package com.samsam.travel.travelcommerce.global.status;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 이 열거형은 다양한 상태 코드와 해당 메시지를 나타냅니다.
 *
 * @author lavin
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum CommonCode {

    //User
    /**
     * 회원가입이 성공적으로 완료되었음을 나타내는 상태 코드.
     */
    SUCCESS_SIGN_UP(HttpStatus.OK, "2000", "%s 회원가입이 완료되었습니다."),

    /**
     * 로그인이 성공적으로 완료되었음을 나타내는 상태 코드.
     */
    SUCCESS_LOGIN(HttpStatus.OK, "2001", "로그인이 완료되었습니다."),

    /**
     * 유저 정보 조회가 성공적으로 완료되었음을 나타내는 상태 코드.
     */
    SUCCESS_USER_INFO(HttpStatus.OK, "2002", "유저 정보 조회가 완료되었습니다."),

    /**
     * 전체 유저 정보 조회가 성공적으로 완료되었음을 나타내는 상태 코드.
     */
    SUCCESS_ALL_USER_INFO(HttpStatus.OK, "2003", "전체 유저 정보 조회가 완료되었습니다."),

    /**
     * 관리자 권한이 성공적으로 부여되었음을 나타내는 상태 코드.
     * %s는 계정 이름으로 대체됩니다.
     */
    SUCCESS_EMPOWERMENT(HttpStatus.OK, "2004", "%s 계정에 관리자 권한이 부여됐습니다."),

    /**
     * 관리자 권한이 성공적으로 박탈되었음을 나타내는 상태 코드.
     * %s는 계정 이름으로 대체됩니다.
     */
    SUCCESS_DEPRIVATION(HttpStatus.OK, "2005", "%s 계정에 관리자 권한이 박탈됐습니다."),

    ;

    private final HttpStatus httpStatus;
    private final String statusCode;
    private final String message;
}