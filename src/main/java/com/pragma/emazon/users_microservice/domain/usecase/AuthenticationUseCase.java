package com.pragma.emazon.users_microservice.domain.usecase;

import com.pragma.emazon.users_microservice.domain.model.Auth;
import com.pragma.emazon.users_microservice.domain.model.AuthToken;
import com.pragma.emazon.users_microservice.domain.port.api.IAuthenticationServicePort;
import com.pragma.emazon.users_microservice.domain.port.spi.IAuthenticationSecurityPort;


public class AuthenticationUseCase implements IAuthenticationServicePort {

    private final IAuthenticationSecurityPort authenticationSecurityPort;

    public AuthenticationUseCase(IAuthenticationSecurityPort authenticationSecurityPort) {

        this.authenticationSecurityPort = authenticationSecurityPort;
    }

    @Override
    public AuthToken authenticate(Auth auth) {

        return authenticationSecurityPort.authenticate(auth);
    }
}
