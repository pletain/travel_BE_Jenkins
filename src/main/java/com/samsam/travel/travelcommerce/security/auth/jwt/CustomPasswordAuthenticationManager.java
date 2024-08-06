package com.samsam.travel.travelcommerce.security.auth.jwt;

import com.samsam.travel.travelcommerce.domain.user.repository.UserRepository;
import com.samsam.travel.travelcommerce.entity.User;
import com.samsam.travel.travelcommerce.global.status.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

/**
 * 로그인 시, DB와 연결되어 password를 확인하고
 * 인증을 위한 토큰을 만드는 작업을 하는 객체입니다.
 *
 * @author lavin
 * @since 1.0
 */
@Component
@RequiredArgsConstructor
public class CustomPasswordAuthenticationManager implements AuthenticationProvider {
    private final UserRepository userRepository;

    /**
     * 사용자 인증을 수행합니다.
     *
     * @param authentication 사용자 인증 정보
     * @return 사용자 인증 결과
     * @throws AuthenticationException 사용자 인증에 실패할 경우
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional<User> optionalUser = userRepository.findById(authentication.getPrincipal().toString());
        if (optionalUser.isEmpty()) {
            throw new BadCredentialsException(ErrorCode.NOT_EXIST_USER.getMessage());
        }
        User user = optionalUser.get();
        if (!authentication.getCredentials().equals(user.getPassword())) {
            throw new BadCredentialsException(ErrorCode.USER_PASSWORD_NOT_MATCHED.getMessage());
        }
        CustomPasswordAuthenticationToken token = new CustomPasswordAuthenticationToken(
                user.getUserId(), user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(user.getRole().toString()))
        );
        token.setId(user.getUserId());
        token.setName(user.getName());
        token.setRole(String.valueOf(user.getRole()));
        return token;
    }


    /**
     * 이 AuthenticationProvider가 지원하는 Authentication 클래스인지 확인합니다.
     *
     * @param authentication Authentication 클래스
     * @return 이 AuthenticationProvider가 지원하면 true, 지원하지 않으면 false
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(CustomPasswordAuthenticationToken.class);
    }
}
