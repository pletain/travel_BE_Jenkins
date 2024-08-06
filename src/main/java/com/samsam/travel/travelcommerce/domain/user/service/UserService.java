package com.samsam.travel.travelcommerce.domain.user.service;

import com.samsam.travel.travelcommerce.domain.user.repository.UserRepository;
import com.samsam.travel.travelcommerce.dto.user.UserInfoResponse;
import com.samsam.travel.travelcommerce.global.error.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.samsam.travel.travelcommerce.global.status.ErrorCode.NOT_EXIST_USER;

/**
 * 이 클래스는 사용자 관련 서비스를 제공합니다.
 *
 *  * @author lavin
 *  * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * 사용자 ID를 기반으로 사용자 정보를 가져옵니다.
     *
     * @param userId 사용자 ID
     * @return 사용자 정보
     * @throws UserNotFoundException 사용자가 존재하지 않는 경우
     */
    public UserInfoResponse getUserInfo(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(NOT_EXIST_USER))
                .toUserInfoResponse();
    }
}

