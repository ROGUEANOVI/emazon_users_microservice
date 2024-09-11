package com.pragma.emazon.users_microservice.domain.usecase;

import com.pragma.emazon.users_microservice.domain.model.Auth;
import com.pragma.emazon.users_microservice.domain.model.AuthToken;
import com.pragma.emazon.users_microservice.domain.port.spi.IAuthenticationSecurityPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AuthenticationUseCaseTest {

    @Mock
    private IAuthenticationSecurityPort authenticationSecurityPort;

    @InjectMocks
    private AuthenticationUseCase authenticationUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAuthenticate() {
        // Arrange
        Auth auth = new Auth("username", "password");
        AuthToken expectedToken = new AuthToken("token123");

        // Mock the behavior of authenticationSecurityPort
        when(authenticationSecurityPort.authenticate(auth)).thenReturn(expectedToken);

        // Act
        AuthToken actualToken = authenticationUseCase.authenticate(auth);

        // Assert
        assertEquals(expectedToken, actualToken);
    }
}
