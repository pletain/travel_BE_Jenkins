package com.samsam.travel.travelcommerce.domain.user.api;

import com.samsam.travel.travelcommerce.domain.user.service.UserAuthService;
import com.samsam.travel.travelcommerce.dto.user.LoginRequest;
import com.samsam.travel.travelcommerce.dto.user.LoginResponse;
import com.samsam.travel.travelcommerce.dto.user.SignUpRequest;
import com.samsam.travel.travelcommerce.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.samsam.travel.travelcommerce.global.status.CommonCode.SUCCESS_LOGIN;
import static com.samsam.travel.travelcommerce.global.status.CommonCode.SUCCESS_SIGN_UP;

/**
 * 로그인, 회원가입 등 인증 관련 API를 처리하는 컨트롤러입니다.
 *
 * @author lavin
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
     * 로그인 API
     *
     * @param loginRequest 로그인 요청 데이터
     * @return 로그인 성공 여부와 로그인 응답 데이터
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = userAuthService.login(loginRequest);
        ApiResponse<LoginResponse> response = ApiResponse.createResponse(SUCCESS_LOGIN, loginResponse);
        return ResponseEntity.status(SUCCESS_LOGIN.getHttpStatus()).body(response);
    }

    /**
     * 회원 가입 API
     *
     * 이 메서드는 {@link UserAuthService#signUp(SignUpRequest)} 메서드를 호출하여 회원가입 요청을 처리합니다.
     * 그런 다음, 제공된 {@link SignUpRequest#getId()}와 {@link com.samsam.travel.travelcommerce.global.status.CommonCode#SUCCESS_SIGN_UP} 상수를 사용하여 성공 응답 메시지를 구성합니다.
     * 마지막으로, 적절한 HTTP 상태 코드와 구성된 응답을 포함하는 ResponseEntity를 반환합니다.
     *
     * @param signUpRequest 회원가입 요청 데이터
     * @return API 응답을 포함하는 ResponseEntity. HTTP 상태 코드와 구성된 응답이 있습니다.
     */
    @PostMapping("/signUp")
    public ResponseEntity<ApiResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        userAuthService.signUp(signUpRequest);
        String formattedMessage = String.format(SUCCESS_SIGN_UP.getMessage(), signUpRequest.getId());
        ApiResponse response = ApiResponse.createResponse(SUCCESS_SIGN_UP, formattedMessage);
        return ResponseEntity.status(SUCCESS_SIGN_UP.getHttpStatus()).body(response);
    }
}