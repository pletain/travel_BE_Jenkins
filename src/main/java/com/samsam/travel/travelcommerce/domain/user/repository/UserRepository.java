package com.samsam.travel.travelcommerce.domain.user.repository;

import com.samsam.travel.travelcommerce.entity.User;
import com.samsam.travel.travelcommerce.entity.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String> {

    /**
     * 사용자 ID로 역할을 찾아 반환합니다.
     *
     * @param userId 사용자 ID.
     * @return 사용자 ID에 해당하는 역할.
     */
    @Query("select u.role from User u where u.userId = ?1")
    Role findRoleByUserId(String userId);
}