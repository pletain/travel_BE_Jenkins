package com.samsam.travel.travelcommerce.security.auth.jwt;

import com.samsam.travel.travelcommerce.global.status.ErrorCode;
import com.samsam.travel.travelcommerce.security.auth.AuthToken;
import com.samsam.travel.travelcommerce.security.error.exception.JwtTokenException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

/**
 * JWT 토큰을 만들기 위한 key 값과 만들어진 JWT 토큰을 갖는 객체
 * getData() 메서드를 통해 JWT 토큰 안의 데이터를 가져올 수 있습니다.
 */
@Slf4j
public class JwtAuthToken implements AuthToken<Claims> {
    /**
     * JWT 토큰 안에 있는 role 정보를 담고 있는 key 값
     */
    public static final String AUTHORITIES_KEY = "role";

    /**
     * JWT 토큰
     * -- GETTER --
     *  JWT 토큰을 반환합니다.
     *
     * @return JWT 토큰

     */
    @Getter
    private final String token;

    /**
     * JWT 토큰을 암호화할 때 사용하는 key
     */
    private final Key key;

    /**
     * JWT 토큰을 생성합니다.
     *
     * @param token JWT 토큰
     * @param key JWT 토큰을 암호화할 때 사용하는 key
     */
    public JwtAuthToken(String token, Key key) {
        this.token = token;
        this.key = key;
    }

    /**
     * JWT 토큰을 생성합니다.
     *
     * @param id 사용자 ID
     * @param key JWT 토큰을 암호화할 때 사용하는 key
     * @param role 사용자 역할
     * @param claims JWT 토큰 안에 담을 추가 정보
     * @param expiredDate JWT 토큰의 만료 날짜
     * @throws JwtTokenException JWT 토큰 생성에 실패할 경우
     */
    public JwtAuthToken(String id, Key key, String role,
                        Map<String, String> claims, Date expiredDate) {
        this.key = key;
        this.token = createJwtToken(id, role, claims, expiredDate)
                .orElseThrow(()-> new JwtTokenException(ErrorCode.JWT_MALFORMED));
    }

    /**
     * JWT 토큰의 유효성을 검사합니다.
     *
     * @return JWT 토큰이 유효하면 true, 유효하지 않으면 false
     * @throws JwtTokenException JWT 토큰이 유효하지 않을 경우
     */
    @Override
    public boolean validate() throws JwtTokenException {
        return getData() != null;
    }

    /**
     * JWT 토큰 안에 있는 데이터를 반환합니다.
     *
     * @return JWT 토큰 안에 있는 데이터
     * @throws JwtTokenException JWT 토큰이 유효하지 않을 경우
     */
    @Override
    public Claims getData() {
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.error("만료된 JWT 토큰입니다.");
            throw new JwtTokenException(ErrorCode.JWT_EXPIRED);
        }catch (MalformedJwtException e) {
            log.error("유효하지 않은 JWT 토큰입니다.");
            throw new JwtTokenException(ErrorCode.JWT_MALFORMED);
        }catch (UnsupportedJwtException e) {
            log.error("지원하지 않는 JWT 토큰입니다.");
            throw new JwtTokenException(ErrorCode.JWT_UNSUPPORTED);
        } catch (IllegalArgumentException e) {
            log.error("JWT 토큰 핸들러의 compact 형식이 유효하지 않습니다.");
            throw new JwtException("JWT 토큰 핸들러의 compact 형식이 유효하지 않습니다.");
        }
    }

    /**
     * JWT 토큰을 생성합니다.
     *
     * @param id 사용자 ID
     * @param role 사용자 역할
     * @param claimsMap JWT 토큰 안에 담을 추가 정보
     * @param expiredDate JWT 토큰의 만료 날짜
     * @return JWT 토큰
     */
    private Optional<String> createJwtToken(String id, String role,
                                            Map<String, String> claimsMap, Date expiredDate) {
        Claims claims = new DefaultClaims(claimsMap);
        claims.put(AUTHORITIES_KEY, role);
        return Optional.ofNullable(Jwts.builder()
                .setSubject(id)
                .addClaims(claims)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(expiredDate)
                .compact()
        );
    }
}
