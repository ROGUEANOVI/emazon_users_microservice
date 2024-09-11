package com.pragma.emazon.users_microservice.application.handler;

import com.pragma.emazon.users_microservice.application.dto.request.AuthRequest;
import com.pragma.emazon.users_microservice.application.dto.response.TokenResponse;
import com.pragma.emazon.users_microservice.application.mapper.AuthRequestMapper;
import com.pragma.emazon.users_microservice.domain.model.Auth;
import com.pragma.emazon.users_microservice.domain.model.AuthToken;
import com.pragma.emazon.users_microservice.domain.port.api.IAuthenticationServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationHandler implements IAuthenticationHandler {

    private final IAuthenticationServicePort authenticationServicePort;

    private final AuthRequestMapper authRequestMapper;

    @Override
    public TokenResponse authenticate(AuthRequest authRequest) {

        Auth auth = authRequestMapper.toAuth(authRequest);
        AuthToken authToken = authenticationServicePort.authenticate(auth);
        return authRequestMapper.toTokenResponse(authToken);
    }
}
