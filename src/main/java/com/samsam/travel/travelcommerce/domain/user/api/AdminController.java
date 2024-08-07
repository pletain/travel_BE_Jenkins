package com.samsam.travel.travelcommerce.domain.user.api;

import com.samsam.travel.travelcommerce.domain.user.service.AdminService;
import com.samsam.travel.travelcommerce.dto.user.UserInfoResponse;
import com.samsam.travel.travelcommerce.utils.ApiResponse;
import com.samsam.travel.travelcommerce.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.samsam.travel.travelcommerce.global.status.CommonCode.SUCCESS_ALL_USER_INFO;

/**
 * 이 컨트롤러는 관리자 관련 API를 처리하는 컨트롤러입니다.
 *
 * @since 1.0
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    /**
     * 모든 사용자 정보를 가져옵니다.
     *
     * @param userDetails 인증된 관리자의 세부 정보. Spring Security 컨텍스트에서 가져옵니다.
     * @return ResponseEntity에 ApiResponse를 포함하고 있는 ResponseEntity. ApiResponse에는 상태 코드 200(OK)이 있으며, data 필드에 모든 사용자 정보가 있습니다.
     * @since 1.0
     */
    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<UserInfoResponse>>> fetchAllUsers(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("getAllUserInfo called by {}", userDetails.getUsername());
        List<UserInfoResponse> userInfoResponses = adminService.getAllUserInfo(userDetails.getUsername());
        return ResponseUtil.createApiResponse(SUCCESS_ALL_USER_INFO, userInfoResponses);
    }
}