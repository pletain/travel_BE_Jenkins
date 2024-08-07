package com.samsam.travel.travelcommerce.domain.user.api;

import com.samsam.travel.travelcommerce.domain.user.service.AdminService;
import com.samsam.travel.travelcommerce.dto.user.UserInfoResponse;
import com.samsam.travel.travelcommerce.utils.ApiResponse;
import com.samsam.travel.travelcommerce.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.samsam.travel.travelcommerce.global.status.CommonCode.SUCCESS_ALL_USER_INFO;
import static com.samsam.travel.travelcommerce.global.status.CommonCode.SUCCESS_ASSIGN_ROLE;

/**
 * 관리자 관련 API 요청을 처리하는 컨트롤러입니다.
 *
 * @author lavin
 * @since 1.0
 */
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
        List<UserInfoResponse> userInfoResponses = adminService.getAllUserInfo(userDetails.getUsername());
        return ResponseUtil.createApiResponse(SUCCESS_ALL_USER_INFO, userInfoResponses);
    }

    /**
     * 지정된 사용자에게 새로운 역할을 할당합니다.
     *
     * @param userDetails 인증된 관리자의 세부 정보. Spring Security 컨텍스트에서 가져옵니다.
     * @param userId       역할을 할당할 사용자 ID.
     * @param newRole      새로운 역할.
     * @return ResponseEntity<ApiResponse>에 ApiResponse를 포함하고 있는 ResponseEntity. ApiResponse에는 상태 코드 200(OK)이 있으며, data 필드에 성공 메시지가 있습니다.
     * @since 1.0
     */
    @PutMapping("/users/{userId}/role")
    public ResponseEntity<ApiResponse> assignRoleToUser(@AuthenticationPrincipal UserDetails userDetails, @PathVariable String userId, @RequestParam String newRole) {
        adminService.updateUserRole(userId, newRole, userDetails.getUsername());
        String formattedMessage = String.format(SUCCESS_ASSIGN_ROLE.getMessage(), userId, newRole);
        return ResponseUtil.createApiResponse(SUCCESS_ASSIGN_ROLE, formattedMessage);
    }
}