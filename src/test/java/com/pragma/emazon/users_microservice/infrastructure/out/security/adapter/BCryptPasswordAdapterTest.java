package com.pragma.emazon.users_microservice.infrastructure.out.security.adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BCryptPasswordAdapterTest {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private BCryptPasswordSecurityAdapter bCryptPasswordAdapter;

    @BeforeEach
    void setUp() {
        bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
        bCryptPasswordAdapter = new BCryptPasswordSecurityAdapter();
    }

    @Test
    void encryptPasswordShouldReturnEncodedPassword() {
        // Arrange
        String rawPassword = "myPassword";
        String encodedPassword = "$2a$10$DOWSDfFJFLOpsIOJF/ADKEFIJEOF";

        // Mock the behavior of bCryptPasswordEncoder
        when(bCryptPasswordEncoder.encode(rawPassword)).thenReturn(encodedPassword);

        // Act
        String result = bCryptPasswordAdapter.encryptPassword(rawPassword);

        // Assert
        assertNotNull(result);
        assertTrue(result.startsWith("$2a$"));
    }

    @Test
    void encryptPasswordShouldEncryptPasswordWithDifferentHashes() {
        // Arrange
        String rawPassword = "myPassword";

        // Act
        String encodedPassword1 = bCryptPasswordAdapter.encryptPassword(rawPassword);
        String encodedPassword2 = bCryptPasswordAdapter.encryptPassword(rawPassword);

        // Assert
        assertNotNull(encodedPassword1);
        assertNotNull(encodedPassword2);
        assertTrue(encodedPassword1.startsWith("$2a$"));
        assertTrue(encodedPassword2.startsWith("$2a$"));
        assertNotEquals(encodedPassword1, encodedPassword2); // Passwords should be different
    }
}

