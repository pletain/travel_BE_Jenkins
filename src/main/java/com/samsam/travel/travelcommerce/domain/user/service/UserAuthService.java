package com.samsam.travel.travelcommerce.domain.user.service;

import com.samsam.travel.travelcommerce.domain.user.repository.UserRepository;
import com.samsam.travel.travelcommerce.dto.user.LoginRequest;
import com.samsam.travel.travelcommerce.dto.user.LoginResponse;
import com.samsam.travel.travelcommerce.dto.user.SignUpRequest;
import com.samsam.travel.travelcommerce.entity.User;
import com.samsam.travel.travelcommerce.entity.model.Role;
import com.samsam.travel.travelcommerce.global.error.exception.UserDuplicateException;
import com.samsam.travel.travelcommerce.global.status.ErrorCode;
import com.samsam.travel.travelcommerce.security.auth.jwt.CustomPasswordAuthenticationToken;
import com.samsam.travel.travelcommerce.security.auth.jwt.JwtAuthToken;
import com.samsam.travel.travelcommerce.security.auth.jwt.JwtAuthTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

/**
 * 이 클래스는 사용자 인증 및 권한 부여 서비스를 제공합니다.
 * 로그인 및 회원 가입을 위한 메서드를 포함합니다.
 *
 * @author lavin
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class UserAuthService {
    /**
     * 사용자 저장소
     */
    private final UserRepository userRepository;

    /**
     * 인증 관리자
     */
    private final AuthenticationManager authenticationManager;

    /**
     * JWT 토큰 공급자
     */
    private final JwtAuthTokenProvider tokenProvider;

    /**
     * 회원 가입 API
     *
     * @param dto 회원 가입 요청 데이터
     * @throws UserDuplicateException 사용자 ID 중복
     */
    public void signUp(SignUpRequest dto) throws UserDuplicateException {
        Optional<User> optionalUser = userRepository.findById(dto.getId());
        if (optionalUser.isPresent())
            throw new UserDuplicateException(ErrorCode.USER_ID_DUPLICATE);
        User newUser = User.builder()
                .userId(dto.getId())
                .password(dto.getPassword())
                .name(dto.getName())
                .role(Role.NORMAL)
                .phone(dto.getPhone())
                .registDate(LocalDateTime.now())
                .build();
        userRepository.save(newUser);
    }

    /**
     * 성공적인 로그인을 위해 사용자를 인증하고 JWT 토큰을 생성합니다.
     *
     * @param dto 사용자의 아이디와 비밀번호가 포함된 로그인 요청입니다.
     * @return JWT 토큰과 사용자 역할을 포함하는 LoginResponse 개체입니다.
     * @throws AuthenticationException If the authentication fails.
     */
    public LoginResponse login(LoginRequest dto) throws AuthenticationException {
        CustomPasswordAuthenticationToken token = new CustomPasswordAuthenticationToken(
                dto.getId(), dto.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(token);

        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findAny().orElseThrow(()
                        -> new BadCredentialsException("Role information is missing."));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = createToken((CustomPasswordAuthenticationToken) authentication);
        return new LoginResponse(jwtToken, role);
    }

    /**
     * JWT 토큰 생성
     *
     * @param token 사용자 인증 토큰
     * @return JWT 토큰
     */
    private String createToken(CustomPasswordAuthenticationToken token) {
        Map<String, String> claims = Map.of(
                "id", token.getId(),
                "name", token.getName(),
                "role", token.getRole()
        );

        JwtAuthToken jwtAuthToken = tokenProvider.createAuthToken(
                token.getPrincipal().toString(),
                token.getAuthorities().iterator().next().getAuthority(),
                claims
        );
        return jwtAuthToken.getToken();
    }
}
