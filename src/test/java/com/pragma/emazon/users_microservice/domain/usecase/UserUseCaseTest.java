package com.pragma.emazon.users_microservice.domain.usecase;

import com.pragma.emazon.users_microservice.domain.constant.RoleNames;
import com.pragma.emazon.users_microservice.domain.exception.InvalidUserBirthDateException;
import com.pragma.emazon.users_microservice.domain.exception.RoleNotFoundException;
import com.pragma.emazon.users_microservice.domain.exception.UserAlreadyExistsException;
import com.pragma.emazon.users_microservice.domain.exception.UserIsNotAdultException;
import com.pragma.emazon.users_microservice.domain.model.Role;
import com.pragma.emazon.users_microservice.domain.model.User;
import com.pragma.emazon.users_microservice.domain.port.spi.IBCryptPasswordSecurityPort;
import com.pragma.emazon.users_microservice.domain.port.spi.IRolePersistencePort;
import com.pragma.emazon.users_microservice.domain.port.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IRolePersistencePort rolePersistencePort;

    @Mock
    private IBCryptPasswordSecurityPort bCryptPasswordPort;

    @InjectMocks
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createWarehouseAssistantUserShouldCreateUserSuccessfully() {

        // Arrange
        User user = new User();
        user.setFirstName("Pepito");
        user.setLastName("Perez");
        user.setDocumentId("123456789");
        user.setPhoneNumber("+1234567890");
        user.setBirthDate("2000-01-01");
        user.setEmail("test@example.com");
        user.setPassword("password");

        Role role = new Role();
        role.setName(RoleNames.ROLE_WAREHOUSE_ASSISTANT);

        when(userPersistencePort.existsUserByDocumentId(anyString())).thenReturn(false);
        when(userPersistencePort.existsUserByEmail(anyString())).thenReturn(false);
        when(rolePersistencePort.findRoleByName(RoleNames.ROLE_WAREHOUSE_ASSISTANT)).thenReturn(Optional.of(role));
        when(bCryptPasswordPort.encryptPassword("password")).thenReturn("encryptedPassword");

        // Act
        userUseCase.createWarehouseAssistantUser(user);

        // Assert
        verify(rolePersistencePort).findRoleByName(RoleNames.ROLE_WAREHOUSE_ASSISTANT);
        verify(bCryptPasswordPort).encryptPassword("password");
        verify(userPersistencePort).createUser(user);
    }

    @Test
    void createWarehouseAssistantUserShouldThrowExceptionWhenBirthDateNoMatchWithDateFormat() {
        // Arrange
        User user = new User();
        user.setFirstName("Pepito");
        user.setLastName("Perez");
        user.setDocumentId("123456789");
        user.setPhoneNumber("+1234567890");
        user.setBirthDate("2005-08-32");
        user.setEmail("test@example.com");
        user.setPassword("password");

        // Act & Assert
        assertThrows(InvalidUserBirthDateException.class, () -> userUseCase.createWarehouseAssistantUser(user));

        verify(userPersistencePort, never()).createUser(any());
    }

    @Test
    void createWarehouseAssistantUserShouldThrowExceptionWhenInvalidBirthDateIsInFuture() {
        // Arrange
        User user = new User();
        user.setFirstName("Pepito");
        user.setLastName("Perez");
        user.setDocumentId("123456789");
        user.setPhoneNumber("+1234567890");
        user.setBirthDate("2050-01-01");
        user.setEmail("test@example.com");
        user.setPassword("password");

        // Act & Assert
        assertThrows(InvalidUserBirthDateException.class, () -> userUseCase.createWarehouseAssistantUser(user));

        verify(userPersistencePort, never()).createUser(any());
    }

    @Test
    void createWarehouseAssistantUserShouldThrowExceptionWhenUserIsNotAdult() {
        // Arrange
        User user = new User();
        user.setFirstName("Pepito");
        user.setLastName("Perez");
        user.setDocumentId("123456789");
        user.setPhoneNumber("+1234567890");
        user.setBirthDate("2010-01-01");
        user.setEmail("test@example.com");
        user.setPassword("password");

        // Act & Assert
        assertThrows(UserIsNotAdultException.class, () -> userUseCase.createWarehouseAssistantUser(user));

        verify(userPersistencePort, never()).createUser(any());
    }

    @Test
    void createWarehouseAssistantUserShouldThrowExceptionWhenDocumentIdAlreadyExists() {
        // Arrange
        User user = new User();
        user.setFirstName("Pepito");
        user.setLastName("Perez");
        user.setDocumentId("123456789");
        user.setPhoneNumber("+1234567890");
        user.setBirthDate("2000-01-01");
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(userPersistencePort.existsUserByDocumentId(anyString())).thenReturn(true);

        // Act & Assert
        assertThrows(UserAlreadyExistsException.class, () -> userUseCase.createWarehouseAssistantUser(user));

        verify(userPersistencePort, never()).createUser(any());
    }

    @Test
    void createWarehouseAssistantUserShouldThrowExceptionWhenEmailAlreadyExists() {
        // Arrange
        User user = new User();
        user.setFirstName("Pepito");
        user.setLastName("Perez");
        user.setDocumentId("123456789");
        user.setPhoneNumber("+1234567890");
        user.setBirthDate("2000-01-01");
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(userPersistencePort.existsUserByEmail(anyString())).thenReturn(true);

        // Act & Assert
        assertThrows(UserAlreadyExistsException.class, () -> userUseCase.createWarehouseAssistantUser(user));

        verify(userPersistencePort, never()).createUser(any());
    }

    @Test
    void createWarehouseAssistantUserShouldThrowExceptionWhenRoleNotFound() {
        // Arrange
        User user = new User();
        user.setFirstName("Pepito");
        user.setLastName("Perez");
        user.setDocumentId("123456789");
        user.setPhoneNumber("+1234567890");
        user.setBirthDate("2000-01-01");
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(rolePersistencePort.findRoleByName(RoleNames.ROLE_WAREHOUSE_ASSISTANT)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RoleNotFoundException.class, () -> userUseCase.createWarehouseAssistantUser(user));

        verify(userPersistencePort, never()).createUser(any());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    void createCustomerUserShouldCreateUserSuccessfully() {

        // Arrange
        User user = new User();
        user.setFirstName("Pepito");
        user.setLastName("Perez");
        user.setDocumentId("123456789");
        user.setPhoneNumber("+1234567890");
        user.setBirthDate("2000-01-01");
        user.setEmail("test@example.com");
        user.setPassword("password");

        Role role = new Role();
        role.setName(RoleNames.ROLE_CUSTOMER);

        when(userPersistencePort.existsUserByDocumentId(anyString())).thenReturn(false);
        when(userPersistencePort.existsUserByEmail(anyString())).thenReturn(false);
        when(rolePersistencePort.findRoleByName(RoleNames.ROLE_CUSTOMER)).thenReturn(Optional.of(role));
        when(bCryptPasswordPort.encryptPassword("password")).thenReturn("encryptedPassword");

        // Act
        userUseCase.createCustomerUser(user);

        // Assert
        verify(rolePersistencePort).findRoleByName(RoleNames.ROLE_CUSTOMER);
        verify(bCryptPasswordPort).encryptPassword("password");
        verify(userPersistencePort).createUser(user);
    }

    @Test
    void createCustomerUserShouldThrowExceptionWhenBirthDateNoMatchWithDateFormat() {
        // Arrange
        User user = new User();
        user.setFirstName("Pepito");
        user.setLastName("Perez");
        user.setDocumentId("123456789");
        user.setPhoneNumber("+1234567890");
        user.setBirthDate("2005-08-32");
        user.setEmail("test@example.com");
        user.setPassword("password");

        // Act & Assert
        assertThrows(InvalidUserBirthDateException.class, () -> userUseCase.createCustomerUser(user));

        verify(userPersistencePort, never()).createUser(any());
    }

    @Test
    void createCustomerUserShouldThrowExceptionWhenInvalidBirthDateIsInFuture() {
        // Arrange
        User user = new User();
        user.setFirstName("Pepito");
        user.setLastName("Perez");
        user.setDocumentId("123456789");
        user.setPhoneNumber("+1234567890");
        user.setBirthDate("2050-01-01");
        user.setEmail("test@example.com");
        user.setPassword("password");

        // Act & Assert
        assertThrows(InvalidUserBirthDateException.class, () -> userUseCase.createCustomerUser(user));

        verify(userPersistencePort, never()).createUser(any());
    }

    @Test
    void createCustomerUserShouldThrowExceptionWhenUserIsNotAdult() {
        // Arrange
        User user = new User();
        user.setFirstName("Pepito");
        user.setLastName("Perez");
        user.setDocumentId("123456789");
        user.setPhoneNumber("+1234567890");
        user.setBirthDate("2010-01-01");
        user.setEmail("test@example.com");
        user.setPassword("password");

        // Act & Assert
        assertThrows(UserIsNotAdultException.class, () -> userUseCase.createCustomerUser(user));

        verify(userPersistencePort, never()).createUser(any());
    }

    @Test
    void createCustomerUserShouldThrowExceptionWhenDocumentIdAlreadyExists() {
        // Arrange
        User user = new User();
        user.setFirstName("Pepito");
        user.setLastName("Perez");
        user.setDocumentId("123456789");
        user.setPhoneNumber("+1234567890");
        user.setBirthDate("2000-01-01");
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(userPersistencePort.existsUserByDocumentId(anyString())).thenReturn(true);

        // Act & Assert
        assertThrows(UserAlreadyExistsException.class, () -> userUseCase.createCustomerUser(user));

        verify(userPersistencePort, never()).createUser(any());
    }

    @Test
    void createCustomerUserShouldThrowExceptionWhenEmailAlreadyExists() {
        // Arrange
        User user = new User();
        user.setFirstName("Pepito");
        user.setLastName("Perez");
        user.setDocumentId("123456789");
        user.setPhoneNumber("+1234567890");
        user.setBirthDate("2000-01-01");
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(userPersistencePort.existsUserByEmail(anyString())).thenReturn(true);

        // Act & Assert
        assertThrows(UserAlreadyExistsException.class, () -> userUseCase.createCustomerUser(user));

        verify(userPersistencePort, never()).createUser(any());
    }

    @Test
    void createCustomerUserShouldThrowExceptionWhenRoleNotFound() {
        // Arrange
        User user = new User();
        user.setFirstName("Pepito");
        user.setLastName("Perez");
        user.setDocumentId("123456789");
        user.setPhoneNumber("+1234567890");
        user.setBirthDate("2000-01-01");
        user.setEmail("test@example.com");
        user.setPassword("password");

        when(rolePersistencePort.findRoleByName(RoleNames.ROLE_CUSTOMER)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RoleNotFoundException.class, () -> userUseCase.createCustomerUser(user));

        verify(userPersistencePort, never()).createUser(any());
    }
}
