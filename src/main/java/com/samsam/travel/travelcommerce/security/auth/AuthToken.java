package com.samsam.travel.travelcommerce.security.auth;

/**
 * 인증 토큰 인터페이스로, 보안 인증 및 권한 부여를 위해 사용됩니다.
 *
 * @param <T> 인증 토큰에 캡슐화된 데이터의 유형.
 */
public interface AuthToken <T> {

    /**
     * 인증 토큰의 유효성을 검증합니다.
     *
     * @return 토큰이 유효하면 {@code true}, 그렇지 않으면 {@code false}를 반환합니다.
     */
    boolean validate();

    /**
     * 인증 토큰에 캡슐화된 데이터를 검색합니다.
     *
     * @return 인증 토큰에 캡슐화된 데이터.
     */
    T getData();
}
