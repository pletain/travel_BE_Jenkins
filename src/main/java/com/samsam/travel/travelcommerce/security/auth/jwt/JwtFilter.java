package com.samsam.travel.travelcommerce.security.auth.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

/**
 * Authorization 헤더에 있는 토큰 값을 읽어와
 * Spring에서 사용하는 인증 객체로 만들어주는 필터
 */
@Component
public class JwtFilter extends OncePerRequestFilter {
    /**
     * Authorization 헤더에서 JWT 토큰이 위치한 이름
     */
    @Value("${jwt.header}")
    private String authHeaderName;

    /**
     * Authorization 헤더에서 JWT 토큰의 접두사
     */
    @Value("${jwt.prefix}")
    private String prefix;

    /**
     * JWT 인증 토큰 공급자
     */
    private final JwtAuthTokenProvider tokenProvider;

    /**
     * JwtFilter 클래스의 새 인스턴스를 생성합니다.
     *
     * @param tokenProvider JWT 인증 토큰 공급자
     */
    public JwtFilter(JwtAuthTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    /**
     * 필터링 작업을 수행합니다.
     * Authorization 헤더에서 JWT 토큰을 읽어서, 유효성을 검사하고,
     * Spring에서 사용할 수 있는 인증 객체로 변환합니다.
     *
     * @param request HTTP 요청
     * @param response HTTP 응답
     * @param filterChain 필터 체인
     * @throws ServletException 필터링 작업 중에 오류가 발생할 경우
     * @throws IOException 필터링 작업 중에 오류가 발생할 경우
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Optional<String> token = resolveToken(request);
        if (token.isPresent()) {
            JwtAuthToken jwtAuthToken = tokenProvider.convertAuthToken(token.get());
            if (jwtAuthToken.validate()) {
                Authentication authentication = tokenProvider.getAuthentication(jwtAuthToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Authorization 헤더에서 JWT 토큰을 해석합니다.
     *
     * @param request HTTP 요청
     * @return JWT 토큰이 존재하면 Optional로 반환하고, 존재하지 않으면 빈 Optional을 반환합니다.
     */
    private Optional<String> resolveToken(HttpServletRequest request) {
        String authHeader = request.getHeader(authHeaderName);
        if (!StringUtils.hasText(authHeader))
            return Optional.empty();
        String[] str = authHeader.split(" ");
        if (str.length != 2 || !str[0].equals(prefix) || str[1].length() == 0)
            return Optional.empty();
        return Optional.of(str[1]);
    }
}
