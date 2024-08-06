package com.samsam.travel.travelcommerce.entity;

import com.samsam.travel.travelcommerce.dto.user.UserInfoResponse;
import com.samsam.travel.travelcommerce.entity.model.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id", length = 255)
    private String userId;

    @Column(name = "password", nullable = false, length = 255)
    @NotNull
    private String password;

    @Column(name = "name", nullable = false, length = 255)
    @NotNull
    private String name;

    @Column(name = "phone", nullable = false, length = 255)
    @NotNull
    private String phone;

    @Column(name = "role", nullable = false, length = 255)
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "regist_date", nullable = false, updatable = false, length = 255)
    @NotNull
    private LocalDateTime registDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    /**
     * 현재 User 엔티티를 UserInfoResponse 객체로 변환합니다.
     * 이 메서드는 외부 시스템이나 클라이언트에게 User 엔티티의 간단한 표현을 제공하기 위해 사용됩니다.
     *
     * @return UserInfoResponse 객체.
     */
    public UserInfoResponse toUserInfoResponse() {
        return UserInfoResponse.builder()
                .id(this.userId)
                .name(this.name)
                .phone(this.phone)
                .role(this.role)
                .build();
    }
}