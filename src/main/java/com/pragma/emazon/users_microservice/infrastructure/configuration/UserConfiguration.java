package com.pragma.emazon.users_microservice.infrastructure.configuration;

import com.pragma.emazon.users_microservice.domain.port.api.IUserServicePort;
import com.pragma.emazon.users_microservice.domain.port.spi.IBCryptPasswordSecurityPort;
import com.pragma.emazon.users_microservice.domain.port.spi.IRolePersistencePort;
import com.pragma.emazon.users_microservice.domain.port.spi.IUserPersistencePort;
import com.pragma.emazon.users_microservice.domain.usecase.UserUseCase;
import com.pragma.emazon.users_microservice.infrastructure.out.jpa.adapter.UserJpaAdapter;
import com.pragma.emazon.users_microservice.infrastructure.out.jpa.mapper.UserEntityMapper;
import com.pragma.emazon.users_microservice.infrastructure.out.jpa.repository.IUserRepository;
import com.pragma.emazon.users_microservice.infrastructure.out.security.adapter.BCryptPasswordSecurityAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UserConfiguration {

    private final IUserRepository userRepository;

    private final UserEntityMapper userEntityMapper;

    private final IRolePersistencePort rolePersistencePort;

    @Bean
    public IUserPersistencePort userPersistencePort() {

        return new UserJpaAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IBCryptPasswordSecurityPort bCryptPasswordPort() {

        return new BCryptPasswordSecurityAdapter();
    }

    @Bean
    public IUserServicePort userServicePort() {

        return new UserUseCase(userPersistencePort(), rolePersistencePort, bCryptPasswordPort());
    }
}
