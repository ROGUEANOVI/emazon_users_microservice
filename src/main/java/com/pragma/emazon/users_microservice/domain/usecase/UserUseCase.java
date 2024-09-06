package com.pragma.emazon.users_microservice.domain.usecase;

import com.pragma.emazon.users_microservice.domain.constant.RoleExceptionMessages;
import com.pragma.emazon.users_microservice.domain.constant.RoleNames;
import com.pragma.emazon.users_microservice.domain.exception.RoleNotFoundException;
import com.pragma.emazon.users_microservice.domain.model.Role;
import com.pragma.emazon.users_microservice.domain.model.User;
import com.pragma.emazon.users_microservice.domain.port.api.IUserServicePort;
import com.pragma.emazon.users_microservice.domain.port.spi.IBCryptPasswordPort;
import com.pragma.emazon.users_microservice.domain.port.spi.IRolePersistencePort;
import com.pragma.emazon.users_microservice.domain.port.spi.IUserPersistencePort;

import static com.pragma.emazon.users_microservice.domain.validation.UserValidation.*;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    private final IRolePersistencePort rolePersistencePort;

    private final IBCryptPasswordPort bCryptPasswordPort;

    public UserUseCase(
            IUserPersistencePort userPersistencePort,
            IRolePersistencePort rolePersistencePort,
            IBCryptPasswordPort bCryptPasswordPort
    ) {

        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
        this.bCryptPasswordPort = bCryptPasswordPort;
    }

    @Override
    public void createWarehouseAssistantUser(User user) {

        validateBirthDate(user.getBirthDate());
        validateDocumentIdAlreadyExists(userPersistencePort.existsUserByDocumentId(user.getDocumentId()), user.getDocumentId());
        validateEmailAlreadyExists(userPersistencePort.existsUserByEmail(user.getEmail()), user.getEmail());

        Role role = rolePersistencePort.findRoleByName(RoleNames.ROLE_WAREHOUSE_ASSISTANT)
                .orElseThrow(() ->
                    new RoleNotFoundException(RoleExceptionMessages.ROLE_NOT_FOUND, RoleNames.ROLE_WAREHOUSE_ASSISTANT)
                );

        user.setRole(role);

        user.setPassword(bCryptPasswordPort.encryptPassword(user.getPassword()));

        userPersistencePort.createUser(user);
    }
}
