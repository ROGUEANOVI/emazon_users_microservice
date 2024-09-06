package com.pragma.emazon.users_microservice.domain.port.spi;

import com.pragma.emazon.users_microservice.domain.model.User;

public interface IUserPersistencePort {

    void createUser(User user);

    Boolean existsUserByEmail(String email);

    Boolean existsUserByDocumentId(String documentId);
}
