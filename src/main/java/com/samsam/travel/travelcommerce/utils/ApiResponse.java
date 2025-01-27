package com.samsam.travel.travelcommerce.utils;

import com.samsam.travel.travelcommerce.global.status.CommonCode;
import lombok.Getter;

/**
 * 이 클래스는 일반적인 API 응답 구조를 나타냅니다. 상태 코드, 메시지, 데이터로 구성됩니다.
 * 여기서 상태 코드는 프로젝트에서 정의된 상태 코드를 사용합니다.
 * @param <T> 응답에서 반환할 데이터의 유형.
 *
 * @author lavin
 * @since 1.0.0
 */
@Getter
public class ApiResponse<T> {
    private String statusCode;
    private String message;
    private T data;

    /**
     * ApiResponse 클래스의 생성자.
     *
     * @param code CommonCode 객체로, 상태 코드와 메시지를 나타냅니다.
     * @param data 응답에서 반환할 데이터.
     */
    private ApiResponse(CommonCode code, T data) {
        this.statusCode = code.getStatusCode();
        this.message = code.getMessage();
        this.data = data;
    }

    /**
     * ApiResponse 클래스의 생성자.
     * 이 생성자는 CommonCode 객체를 사용하여 상태 코드, 메시지를 초기화하고,
     * 응답에서 반환할 데이터로 null을 설정합니다.
     *
     * @param code CommonCode 객체로, 상태 코드와 메시지를 나타냅니다.
     * @param message 응답에 포함할 메시지.
     */
    private ApiResponse(CommonCode code, String message) {
        this.statusCode = code.getStatusCode();
        this.message = message;
        this.data = null;
    }

    /**
     * ApiResponse 객체를 생성하기 위한 정적 팩토리 메서드.
     *
     * @param code CommonCode 객체로, 상태 코드와 메시지를 나타냅니다.
     * @param data 응답에서 반환할 데이터.
     * @param <T> 응답에서 반환할 데이터의 유형.
     * @return 제공된 상태 코드, 메시지, 데이터로 새 ApiResponse 객체.
     */
    public static <T> ApiResponse<T> createResponse(CommonCode code, T data) {
        return new ApiResponse<>(code, data);
    }

    /**
     * 메시지, null 데이터로 새 ApiResponse 객체를 생성하기 위한 정적 팩토리 메서드.
     *
     * @param code CommonCode 객체로, 상태 코드와 메시지를 나타냅니다.
     * @param message ApiResponse에 포함할 메시지.
     * @return 제공된 상태 코드, 메시지, null 데이터로 새 ApiResponse 객체.
     */
    public static ApiResponse createResponse(CommonCode code, String message) {
        return new ApiResponse<>(code, message);
    }
}