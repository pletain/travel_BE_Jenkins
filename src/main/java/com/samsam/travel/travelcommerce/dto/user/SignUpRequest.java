package com.samsam.travel.travelcommerce.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * 회원 가입 요청 dto
 */
@Getter
@Builder
@AllArgsConstructor
public class SignUpRequest {
    private String id;
    private String password;
    private String name;
    private String phone;
}
