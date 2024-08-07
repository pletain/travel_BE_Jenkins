package com.samsam.travel.travelcommerce.domain.user.service;

import com.samsam.travel.travelcommerce.domain.user.repository.UserRepository;
import com.samsam.travel.travelcommerce.dto.user.UserInfoResponse;
import com.samsam.travel.travelcommerce.entity.model.Role;
import com.samsam.travel.travelcommerce.global.error.exception.UserUnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.samsam.travel.travelcommerce.global.status.ErrorCode.USER_NOT_MASTER;

/**
 * 이 서비스 클래스는 관리자 기능을 제공합니다.
 *
 * @author lavin
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class AdminService {

    /**
     * 사용자 저장소 인터페이스. 데이터베이스 작업을 위해 사용됩니다.
     */
    private final UserRepository userRepository;

    /**
     * 모든 사용자 정보를 가져옵니다.
     *
     * @param userId 인증된 관리자의 사용자 ID.
     * @return 모든 사용자 정보를 포함하는 UserInfoResponse 목록.
     * @throws UserUnauthorizedException 사용자가 관리자가 아닌 경우 발생합니다.
     */
    public List<UserInfoResponse> getAllUserInfo(String userId) {
        if(!userRepository.findRoleByUserId(userId).equals(Role.MASTER)){
            throw new UserUnauthorizedException(USER_NOT_MASTER);
        }
        return userRepository.findAll()
                .stream()
                .map(user -> user.toUserInfoResponse())
                .toList();
    }
}
