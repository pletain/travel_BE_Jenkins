package com.samsam.travel.travelcommerce.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 로그인 요청 dto
 */
@AllArgsConstructor
@Getter
public class LoginRequest {
    @NotBlank(message = "ID is mandatory")
    private String id;

    @NotBlank(message = "Password is mandatory")
    private String password;

}
