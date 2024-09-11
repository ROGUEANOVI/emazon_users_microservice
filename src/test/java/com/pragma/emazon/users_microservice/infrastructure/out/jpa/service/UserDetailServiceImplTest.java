package com.pragma.emazon.users_microservice.infrastructure.out.jpa.service;

import com.pragma.emazon.users_microservice.domain.constant.AuthValidationMessages;
import com.pragma.emazon.users_microservice.infrastructure.out.jpa.entity.RoleEntity;
import com.pragma.emazon.users_microservice.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.emazon.users_microservice.infrastructure.out.jpa.repository.IUserRepository;
import com.pragma.emazon.users_microservice.infrastructure.out.security.service.UserDetailServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserDetailServiceImplTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserDetailServiceImpl userDetailService;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsernameShouldReturnUserDetailsWhenUserExists() {

        // Arrange
        String email = "test@example.com";
        String password = "password";
        String roleName = "ROLE_WAREHOUSE_ASSISTANT";

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(roleName);

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setPassword(password);
        userEntity.setRole(roleEntity);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(userEntity));

        // Act
        UserDetails userDetails = userDetailService.loadUserByUsername(email);

        // Assert
        assertNotNull(userDetails);
        assertEquals(email, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority(roleName)));
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void loadUserByUsernameShouldThrowUserNotFoundExceptionWhenUserDoesNotExist() {

        // Arrange
        String email = "nonexistent@example.com";

        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act & Assert
        BadCredentialsException exception = assertThrows(BadCredentialsException.class, () -> {
            userDetailService.loadUserByUsername(email);
        });

        assertEquals(AuthValidationMessages.INVALID_CREDENTIALS, exception.getMessage());
        verify(userRepository, times(1)).findByEmail(email);
    }
}
