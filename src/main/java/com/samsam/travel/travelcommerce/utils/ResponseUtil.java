package com.samsam.travel.travelcommerce.utils;

import com.samsam.travel.travelcommerce.global.status.CommonCode;
import org.springframework.http.ResponseEntity;

/**
 * API 응답을 생성하기 위한 유틸리티 클래스입니다.
 */
public class ResponseUtil {

    /**
     * 지정된 상태 코드와 데이터를 사용하여 API 응답을 생성합니다.
     *
     * @param <T>       응답에 포함할 데이터의 유형.
     * @param code      API 응답에 포함할 상태 코드.
     * @param data      API 응답에 포함할 데이터.
     * @return ResponseEntity<ApiResponse<T>> API 응답.
     */
    public static <T> ResponseEntity<ApiResponse<T>> createApiResponse(CommonCode code, T data) {
        ApiResponse<T> response = ApiResponse.createResponse(code, data);
        return ResponseEntity.status(code.getHttpStatus()).body(response);
    }

    /**
     * 지정된 상태 코드와 메시지를 사용하여 API 응답을 생성합니다.
     *
     * @param code      API 응답에 포함할 상태 코드.
     * @param message   API 응답에 포함할 메시지.
     * @return ResponseEntity<ApiResponse> API 응답.
     */
    public static ResponseEntity<ApiResponse> createApiResponse(CommonCode code, String message) {
        ApiResponse response = ApiResponse.createResponse(code, message);
        return ResponseEntity.status(code.getHttpStatus()).body(response);
    }
}