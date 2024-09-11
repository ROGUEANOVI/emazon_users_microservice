package com.pragma.emazon.users_microservice.infrastructure.out.security.adapter;

import com.pragma.emazon.users_microservice.domain.model.Auth;
import com.pragma.emazon.users_microservice.domain.model.AuthToken;
import com.pragma.emazon.users_microservice.infrastructure.out.security.service.UserDetailServiceImpl;
import com.pragma.emazon.users_microservice.infrastructure.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AuthenticationSecurityAdapterTest {

    @Mock
    private UserDetailServiceImpl userDetailService;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @InjectMocks
    private AuthenticationSecurityAdapter authenticationSecurityAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void testAuthenticate() {

        // Arrange
        Auth auth = new Auth("user@example.com", "password123");
        String token = "jwt-token";

        // Mock the behavior of userDetailService
        when(userDetailService.authenticate(auth.getEmail(), auth.getPassword())).thenReturn(authentication);
        when(jwtUtil.createToken(authentication)).thenReturn(token);

        AuthToken expectedAuthToken = new AuthToken();
        expectedAuthToken.setToken(token);

        // Act
        AuthToken actualAuthToken = authenticationSecurityAdapter.authenticate(auth);

        // Assert
        assertEquals(expectedAuthToken.getToken(), actualAuthToken.getToken(), "The token should match the expected value.");
        verify(securityContext).setAuthentication(authentication);
    }

    @Test
    void testAuthenticate_ThrowsBadCredentialsException() {
        // Arrange
        Auth auth = new Auth("user@example.com", "wrongpassword");

        // Mock the behavior of userDetailService to throw BadCredentialsException
        when(userDetailService.authenticate(auth.getEmail(), auth.getPassword()))
                .thenThrow(new BadCredentialsException("Invalid credentials"));

        // Act & Assert
        BadCredentialsException thrownException = null;
        try {
            authenticationSecurityAdapter.authenticate(auth);
        } catch (BadCredentialsException e) {
            thrownException = e;
        }

        // Verify that an exception was thrown and has the correct message
        assertNotNull(thrownException, "A BadCredentialsException should have been thrown.");
        assertEquals("Invalid credentials", thrownException.getMessage(), "Exception message should match the expected error.");
    }

    @Test
    void testAuthenticateTokenCreationFailure() {
        // Arrange
        Auth auth = new Auth("user@example.com", "password123");

        when(userDetailService.authenticate(auth.getEmail(), auth.getPassword())).thenReturn(authentication);
        when(jwtUtil.createToken(authentication)).thenThrow(new RuntimeException("Token creation failed"));

        // Act & Assert
        try {
            authenticationSecurityAdapter.authenticate(auth);
        } catch (RuntimeException e) {
            assertEquals("Token creation failed", e.getMessage(), "Exception message should match the expected error.");
        }

        verify(securityContext).setAuthentication(authentication);
    }
}
