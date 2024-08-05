package com.samsam.travel.travelcommerce.security.auth.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * CustomPasswordAuthenticationToken은 Spring Security에서 제공하는 UsernamePasswordAuthenticationToken을 확장한 클래스입니다.
 * JWT 인증 프로세스 중 사용자 인증 정보를 캡슐화하기 위해 사용됩니다.
 *
 * @author lavin
 * @since 1.0
 */
@Getter
@Setter
public class CustomPasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

    /**
     * 사용자의 고유 식별자입니다.
     */
    private String id;

    /**
     * 사용자의 역할입니다.
     */
    private String role;

    /**
     * 사용자의 이름입니다.
     */
    private String name;

    /**
     * principal(사용자 이름 또는 사용자 객체)과 credentials(사용자 비밀번호)로 CustomPasswordAuthenticationToken을 생성합니다.
     *
     * @param principal 사용자의 principal(사용자 이름 또는 사용자 객체)
     * @param credentials 사용자의 credentials(비밀번호)
     */
    public CustomPasswordAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    /**
     * principal(사용자 이름 또는 사용자 객체), credentials(사용자 비밀번호),
     * 그리고 authorities(사용자에게 부여된 권한)로 CustomPasswordAuthenticationToken을 생성합니다.
     *
     * @param principal 사용자의 principal(사용자 이름 또는 사용자 객체)
     * @param credentials 사용자의 credentials(비밀번호)
     * @param authorities 사용자에게 부여된 권한
     */
    public CustomPasswordAuthenticationToken(
            Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities
    ) {
        super(principal, credentials, authorities);
    }
}
