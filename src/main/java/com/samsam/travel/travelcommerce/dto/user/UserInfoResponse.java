package com.samsam.travel.travelcommerce.dto.user;

import com.samsam.travel.travelcommerce.entity.model.Role;
import lombok.Builder;
import lombok.Getter;

/**
 * 회원 정보 반환 dto
 */
@Getter
@Builder
public class UserInfoResponse {
    private final String id;
    private final String name;
    private final String phone;
    private final Role role;
}
