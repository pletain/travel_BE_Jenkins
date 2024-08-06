package com.samsam.travel.travelcommerce.security.error.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.samsam.travel.travelcommerce.security.error.exception.JwtTokenException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;

/**
 * JwtTokenException을 처리하고 응답 내용을 수정하는 필터입니다.
 *
 * 이 필터는 OncePerRequestFilter를 확장하며, doFilterInternal 메서드를 재정의합니다.
 * 이 메서드는 요청을 처리하고, JwtTokenException이 발생하면 JSON 형식으로 오류 메시지를 반환합니다.
 *
 * @author lavin
 * @since 1.0
 */
/**
 * JwtTokenException을 처리하고 응답 내용을 수정하는 필터입니다.
 *
 * 이 필터는 OncePerRequestFilter를 확장하며, doFilterInternal 메서드를 재정의합니다.
 * 이 메서드는 요청을 처리하고, JwtTokenException이 발생하면 JSON 형식으로 오류 메시지를 반환합니다.
 */
@Component
@Slf4j
public class JwtExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JwtTokenException jwtTokenException) {
            handleException(response, jwtTokenException, jwtTokenException.getErrorCode().getHttpStatus());
        } catch (JwtException jwtException) {
            handleException(response, jwtException, HttpStatus.FORBIDDEN);
        }
    }

    private void handleException(HttpServletResponse response, Exception exception, HttpStatus status) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        response.setStatus(status.value());

        HashMap<String, Object> body = new HashMap<>();
        body.put("message", exception.getMessage());
        if (exception instanceof JwtTokenException) {
            body.put("code", ((JwtTokenException) exception).getErrorCode().name());
        } else {
            body.put("code", "JWT_ERROR");
        }

        log.error("[ERROR]: {}", exception.getMessage());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), body);
    }
}
