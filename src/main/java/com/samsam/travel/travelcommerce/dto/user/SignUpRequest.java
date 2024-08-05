package com.samsam.travel.travelcommerce.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 회원 가입 요청 dto
 */
@Getter
@AllArgsConstructor
public class SignUpRequest {
    private String id;
    private String password;
    private String name;
    private String phone;
}
