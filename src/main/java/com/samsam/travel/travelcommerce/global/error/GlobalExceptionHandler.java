package com.samsam.travel.travelcommerce.global.error;

import com.samsam.travel.travelcommerce.global.error.exception.BusinessException;
import com.samsam.travel.travelcommerce.global.status.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;

/**
 * 이 클래스는 애플리케이션 전역 예외 처리기로, 컨트롤러에서 발생한 예외를 캡처하고
 * 적절한 HTTP 응답과 오류 세부 정보를 반환합니다.
 *
 * @author lavin
 * @since 1.0.0
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * HttpRequestMethodNotSupportedException 예외를 처리합니다.
     *
     * @param e HttpRequestMethodNotSupportedException 발생한 예외.
     * @return HttpRequestMethodNotSupportedException 발생한 경우 ErrorResponse를 포함하는 ResponseEntity
     *         HTTP 405 상태와 METHOD_NOT_ALLOWED 오류 코드로 반환합니다.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("[Error]: handle HttpRequestMethodNotSupportedException", e);
        ErrorResponse response = new ErrorResponse(ErrorCode.METHOD_NOT_ALLOWED);
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * AuthenticationException 예외를 처리합니다.
     *
     * @param e AuthenticationException 발생한 예외.
     * @return AuthenticationException 발생한 경우 ErrorResponse를 포함하는 ResponseEntity
     *         HTTP 401 상태와 LOGIN_FAIL 오류 코드로 반환합니다.
     */
    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<ErrorResponse> handleAuthenticationException(final AuthenticationException e) {
        log.error("[ERROR]: handle Authentication", e);
        ErrorResponse response = new ErrorResponse(e.getMessage(), "LOGIN_FAIL");
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    /**
     * BusinessException 예외를 처리합니다.
     *
     * @param e BusinessException 발생한 예외.
     * @return BusinessException 발생한 경우 ErrorResponse를 포함하는 ResponseEntity
     *         BusinessException에서 오류 코드를 가져온 HTTP 상태로 반환합니다.
     */
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException e) {
        log.error("[Error]: handle BusinessException", e);
        ErrorResponse response = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(response, e.getErrorCode().getHttpStatus());
    }

    /**
     * 모든 다른 예외를 처리합니다.
     *
     * @param e 발생한 예외.
     * @return 발생한 예외에 대해 ErrorResponse를 포함하는 ResponseEntity
     *         HTTP 500 상태와 SERVER_ERROR 오류 코드로 반환합니다.
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("[Error]: unexpected error occurred", e);
        ErrorResponse response = new ErrorResponse(ErrorCode.SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}