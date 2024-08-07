package com.samsam.travel.travelcommerce;

import com.samsam.travel.travelcommerce.domain.user.repository.UserRepository;
import com.samsam.travel.travelcommerce.domain.user.service.AdminService;
import com.samsam.travel.travelcommerce.entity.model.Role;
import com.samsam.travel.travelcommerce.global.error.exception.UserUnauthorizedException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.samsam.travel.travelcommerce.global.status.ErrorCode.USER_NOT_MASTER;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void updateUserRole_shouldThrowUserUnauthorizedException_whenAdminIdIsNotMasterRole() {
        // given
        String userId = "testUserId";
        String newRole = "ADMIN";
        String adminId = "nonMasterAdminId";

        when(userRepository.findRoleByUserId(adminId)).thenReturn(Role.NORMAL);

        // when
        Throwable exception = assertThrows(UserUnauthorizedException.class, () -> adminService.updateUserRole(userId, newRole, adminId));

        // then
        assertThat(exception.getMessage()).isEqualTo(USER_NOT_MASTER.getMessage());
    }
}