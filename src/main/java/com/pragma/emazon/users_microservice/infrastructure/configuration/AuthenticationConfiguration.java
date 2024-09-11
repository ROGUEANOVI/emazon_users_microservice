package com.pragma.emazon.users_microservice.infrastructure.configuration;

import com.pragma.emazon.users_microservice.domain.port.api.IAuthenticationServicePort;
import com.pragma.emazon.users_microservice.domain.port.spi.IAuthenticationSecurityPort;
import com.pragma.emazon.users_microservice.domain.usecase.AuthenticationUseCase;
import com.pragma.emazon.users_microservice.infrastructure.out.security.adapter.AuthenticationSecurityAdapter;
import com.pragma.emazon.users_microservice.infrastructure.out.security.service.UserDetailServiceImpl;
import com.pragma.emazon.users_microservice.infrastructure.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AuthenticationConfiguration {

    private final UserDetailServiceImpl userDetailService;

    private final JwtUtil jwtUtil;

    @Bean
    public IAuthenticationSecurityPort authenticationSecurityPort() {

        return new AuthenticationSecurityAdapter(userDetailService, jwtUtil);
    }

    @Bean
    public IAuthenticationServicePort authenticationServicePort() {

        return new AuthenticationUseCase(authenticationSecurityPort());
    }
}
