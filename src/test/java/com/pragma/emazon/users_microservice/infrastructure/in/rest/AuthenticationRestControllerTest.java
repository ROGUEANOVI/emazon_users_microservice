package com.pragma.emazon.users_microservice.infrastructure.in.rest;

import com.pragma.emazon.users_microservice.application.dto.request.AuthRequest;
import com.pragma.emazon.users_microservice.application.dto.response.TokenResponse;
import com.pragma.emazon.users_microservice.application.handler.IAuthenticationHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticationRestControllerTest {

    @Mock
    private IAuthenticationHandler authHandler;

    @InjectMocks
    private AuthenticationRestController authenticationRestController;

    @Test
    void testAuthenticate() {
        // Arrange
        AuthRequest authRequest = new AuthRequest("username", "password");
        TokenResponse tokenResponse = new TokenResponse("token");
        when(authHandler.authenticate(authRequest)).thenReturn(tokenResponse);

        // Act
        ResponseEntity<TokenResponse> response = authenticationRestController.authenticate(authRequest);

        // Assert
        assertEquals(ResponseEntity.ok(tokenResponse), response);
    }

    @Test
    void testAuthenticateHandlerReturnsNull() {
        // Arrange
        AuthRequest authRequest = new AuthRequest("username", "password");
        when(authHandler.authenticate(authRequest)).thenReturn(null);

        // Act
        ResponseEntity<TokenResponse> response = authenticationRestController.authenticate(authRequest);

        // Assert
        assertEquals(ResponseEntity.ok().build(), response);
    }
}