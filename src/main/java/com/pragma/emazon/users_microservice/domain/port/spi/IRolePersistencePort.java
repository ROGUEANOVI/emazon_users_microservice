package com.pragma.emazon.users_microservice.domain.port.spi;

import com.pragma.emazon.users_microservice.domain.model.Role;

import java.util.Optional;

public interface IRolePersistencePort {

    Optional<Role> findRoleByName(String name);
}
