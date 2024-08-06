package com.samsam.travel.travelcommerce;

import com.samsam.travel.travelcommerce.domain.user.repository.UserRepository;
import com.samsam.travel.travelcommerce.domain.user.service.UserAuthService;
import com.samsam.travel.travelcommerce.dto.user.SignUpRequest;
import com.samsam.travel.travelcommerce.entity.User;
import com.samsam.travel.travelcommerce.entity.model.Role;
import com.samsam.travel.travelcommerce.global.error.exception.UserDuplicateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserAuthServiceTest {

    @Autowired
    private UserAuthService userAuthService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void shouldSaveANewUserWhenSigningUpWithAUniqueUserId() {
        // given
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .id("uniqueId")
                .password("password")
                .name("John Doe")
                .phone("1234567890")
                .build();

        // when
        userAuthService.signUp(signUpRequest);

        // then
        verify(userRepository).save(any(User.class));
    }
    
    
    @Test
    public void shouldThrowUserDuplicateExceptionWhenSigningUpWithAnExistingUserId() {
        // given
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .id("existingId")
                .password("password")
                .name("John Doe")
                .phone("1234567890")
                .build();

        User existingUser = User.builder()
                .userId("existingId")
                .password("password")
                .name("John Doe")
                .role(Role.NORMAL)
                .phone("1234567890")
                .registDate(LocalDateTime.now())
                .build();

        when(userRepository.findById("existingId")).thenReturn(Optional.of(existingUser));

        // when, then
        assertThrows(UserDuplicateException.class, () -> userAuthService.signUp(signUpRequest));
    }
}