package com.samsam.travel.travelcommerce.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

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
    private String password;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "phone", nullable = false, length = 255)
    private String phone;

    @Column(name = "role", nullable = false, length = 255)
    @ColumnDefault("NORMAL") // 기본값을 NORMAL로 설정
    private String role;

    @Column(name = "regist_date", nullable = false, updatable = false, length = 255)
    private LocalDateTime registDate;

    @PrePersist
    protected void onCreate() {
        this.registDate = LocalDateTime.now();
        if (this.role == null) {
            this.role = "NORMAL"; // 기본 권한 값 설정
        }
    }
}