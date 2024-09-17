package com.pragma.emazon.users_microservice.domain.usecase;

import com.pragma.emazon.users_microservice.domain.constant.RoleExceptionMessages;
import com.pragma.emazon.users_microservice.domain.constant.RoleNames;
import com.pragma.emazon.users_microservice.domain.exception.RoleNotFoundException;
import com.pragma.emazon.users_microservice.domain.model.Role;
import com.pragma.emazon.users_microservice.domain.model.User;
import com.pragma.emazon.users_microservice.domain.port.api.IUserServicePort;
import com.pragma.emazon.users_microservice.domain.port.spi.IBCryptPasswordSecurityPort;
import com.pragma.emazon.users_microservice.domain.port.spi.IRolePersistencePort;
import com.pragma.emazon.users_microservice.domain.port.spi.IUserPersistencePort;

import static com.pragma.emazon.users_microservice.domain.validation.UserValidation.*;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    private final IRolePersistencePort rolePersistencePort;

    private final IBCryptPasswordSecurityPort bCryptPasswordPort;

    public UserUseCase(
            IUserPersistencePort userPersistencePort,
            IRolePersistencePort rolePersistencePort,
            IBCryptPasswordSecurityPort bCryptPasswordPort
    ) {

        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
        this.bCryptPasswordPort = bCryptPasswordPort;
    }

    @Override
    public void createWarehouseAssistantUser(User user) {

        createUser(user, RoleNames.ROLE_WAREHOUSE_ASSISTANT);
    }

    @Override
    public void createCustomerUser(User user) {

        createUser(user, RoleNames.ROLE_CUSTOMER);
    }

    private void createUser(User user, String roleName) {

        validateBirthDate(user.getBirthDate());
        validateDocumentIdAlreadyExists(userPersistencePort.existsUserByDocumentId(user.getDocumentId()), user.getDocumentId());
        validateEmailAlreadyExists(userPersistencePort.existsUserByEmail(user.getEmail()), user.getEmail());

        Role role = rolePersistencePort.findRoleByName(roleName)
                .orElseThrow(() ->
                        new RoleNotFoundException(RoleExceptionMessages.ROLE_NOT_FOUND, roleName)
                );

        user.setRole(role);

        user.setPassword(bCryptPasswordPort.encryptPassword(user.getPassword()));

        userPersistencePort.createUser(user);
    }
}
