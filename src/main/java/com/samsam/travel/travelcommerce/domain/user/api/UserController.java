package com.samsam.travel.travelcommerce.domain.user.api;

import com.samsam.travel.travelcommerce.domain.user.service.UserService;
import com.samsam.travel.travelcommerce.dto.user.UserInfoResponse;
import com.samsam.travel.travelcommerce.utils.ApiResponse;
import com.samsam.travel.travelcommerce.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.samsam.travel.travelcommerce.global.status.CommonCode.SUCCESS_USER_INFO;

/**
 * 이 컨트롤러는 사용자 관련 API를 처리하는 컨트롤러입니다.
 *
 * @since 1.0
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class UserController {

    private final UserService userService;

    /**
     * 인증된 사용자에 따라 사용자 정보를 검색합니다.
     *
     * @param userDetails 인증된 사용자의 세부 정보. Spring Security 컨텍스트에서 가져옵니다.
     * @return ResponseEntity에 ApiResponse를 포함하고 있는 ResponseEntity. ApiResponse에는 상태 코드 200(OK)이 있으며, data 필드에 사용자 정보가 있습니다.
     * @since 1.0
     */
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserInfoResponse>> getUserInfo(@AuthenticationPrincipal UserDetails userDetails) {
        UserInfoResponse userInfo = userService.getUserInfo(userDetails.getUsername());
        return ResponseUtil.createApiResponse(SUCCESS_USER_INFO, userInfo);
    }
}