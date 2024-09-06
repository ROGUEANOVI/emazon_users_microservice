package com.pragma.emazon.users_microservice.infrastructure.configuration;

import com.pragma.emazon.users_microservice.domain.port.spi.IRolePersistencePort;
import com.pragma.emazon.users_microservice.infrastructure.out.jpa.adapter.RoleJpaAdapter;
import com.pragma.emazon.users_microservice.infrastructure.out.jpa.mapper.RoleEntityMapper;
import com.pragma.emazon.users_microservice.infrastructure.out.jpa.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RoleConfiguration {

    private final IRoleRepository roleRepository;

    private final RoleEntityMapper roleEntityMapper;

    @Bean
    public IRolePersistencePort rolePersistencePort() {

        return new RoleJpaAdapter(roleRepository, roleEntityMapper);
    }
}
