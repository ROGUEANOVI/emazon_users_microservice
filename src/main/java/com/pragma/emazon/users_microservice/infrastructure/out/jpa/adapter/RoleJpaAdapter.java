package com.pragma.emazon.users_microservice.infrastructure.out.jpa.adapter;

import com.pragma.emazon.users_microservice.domain.model.Role;
import com.pragma.emazon.users_microservice.domain.port.spi.IRolePersistencePort;
import com.pragma.emazon.users_microservice.infrastructure.out.jpa.mapper.RoleEntityMapper;
import com.pragma.emazon.users_microservice.infrastructure.out.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {

    private final IRoleRepository roleRepository;

    private final RoleEntityMapper roleEntityMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<Role> findRoleByName(String name) {

        return roleRepository.findByName(name).map(roleEntityMapper::toRole);
    }
}
