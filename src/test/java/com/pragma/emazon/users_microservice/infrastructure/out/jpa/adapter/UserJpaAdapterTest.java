package com.pragma.emazon.users_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.users_microservice.domain.model.User;
import com.pragma.emazon.users_microservice.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.emazon.users_microservice.infrastructure.out.jpa.mapper.UserEntityMapper;
import com.pragma.emazon.users_microservice.infrastructure.out.jpa.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class UserJpaAdapterTest {

    private IUserRepository userRepository;
    private UserEntityMapper userEntityMapper;
    private UserJpaAdapter userJpaAdapter;

    @BeforeEach
    void setUp() {
        userRepository = mock(IUserRepository.class);
        userEntityMapper = mock(UserEntityMapper.class);
        userJpaAdapter = new UserJpaAdapter(userRepository, userEntityMapper);
    }

    @Test
    void createUserShouldSaveUserSuccessfully() {

        // Arrange
        User user = new User();
        user.setFirstName("Pepito");
        user.setLastName("Perez");
        user.setDocumentId("123456789");
        user.setPhoneNumber("+1234567890");
        user.setBirthDate("2000-01-01");
        user.setEmail("test@example.com");
        user.setPassword("passwordEncrypted");

        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setDocumentId(user.getDocumentId());
        userEntity.setPhoneNumber(user.getPhoneNumber());
        userEntity.setBirthDate(LocalDate.parse(user.getBirthDate()));
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());

        when(userEntityMapper.toUserEntity(user)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);

        // Act
        userJpaAdapter.createUser(user);

        // Assert
        verify(userEntityMapper, times(1)).toUserEntity(user);
        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    void existsUserByEmailShouldReturnTrueWhenUserExists() {

        // Arrange
        String email = "test@example.com";

        when(userRepository.existsByEmail(email)).thenReturn(true);

        // Act
        Boolean exists = userJpaAdapter.existsUserByEmail(email);

        // Assert
        assertTrue(exists);
        verify(userRepository, times(1)).existsByEmail(email);
    }

    @Test
    void existsUserByDocumentIdShouldReturnTrueWhenUserExists() {

        // Arrange
        String documentId = "123456789";

        when(userRepository.existsByDocumentId(documentId)).thenReturn(true);

        // Act
        Boolean exists = userJpaAdapter.existsUserByDocumentId(documentId);

        // Assert
        assertTrue(exists);
        verify(userRepository, times(1)).existsByDocumentId(documentId);
    }

    @Test
    void existsUserByEmailShouldReturnFalseWhenUserDoesNotExist() {

        // Arrange
        String email = "nonexistent@example.com";

        when(userRepository.existsByEmail(email)).thenReturn(false);

        // Act
        Boolean exists = userJpaAdapter.existsUserByEmail(email);

        // Assert
        assertEquals(false, exists);
        verify(userRepository, times(1)).existsByEmail(email);
    }

    @Test
    void existsUserByDocumentIdShouldReturnFalseWhenUserDoesNotExist() {

        // Arrange
        String documentId = "987654321";

        when(userRepository.existsByDocumentId(documentId)).thenReturn(false);

        // Act
        Boolean exists = userJpaAdapter.existsUserByDocumentId(documentId);

        // Assert
        assertEquals(false, exists);
        verify(userRepository, times(1)).existsByDocumentId(documentId);
    }
}
