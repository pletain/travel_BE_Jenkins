package com.samsam.travel.travelcommerce.domain.user.api;

import com.samsam.travel.travelcommerce.domain.user.service.UserAuthService;
import com.samsam.travel.travelcommerce.dto.user.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 로그인, 회원가입 등 인증 관련 API를 처리하는 컨트롤러입니다.
 *
 * @author 당신의 이름
 * @since 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    /**
     * 사용자 인증 서비스
     */
    private final UserAuthService userAuthService;

    /**
     * 회원 가입 API
     *
     * @param signUpRequest 회원 가입 요청 데이터
     * @return 성공 여부
     */
    @PostMapping("/signUp")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest signUpRequest) {
        userAuthService.signUp(signUpRequest);
        return ResponseEntity.ok("SUCCESS");
    }
}
