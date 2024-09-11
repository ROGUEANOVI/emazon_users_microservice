package com.pragma.emazon.users_microservice.infrastructure.out.security.adapter;

import com.pragma.emazon.users_microservice.domain.model.Auth;
import com.pragma.emazon.users_microservice.domain.model.AuthToken;
import com.pragma.emazon.users_microservice.domain.port.spi.IAuthenticationSecurityPort;
import com.pragma.emazon.users_microservice.infrastructure.out.security.service.UserDetailServiceImpl;
import com.pragma.emazon.users_microservice.infrastructure.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RequiredArgsConstructor
public class AuthenticationSecurityAdapter implements IAuthenticationSecurityPort {

    private final UserDetailServiceImpl userDetailService;

    private final JwtUtil jwtUtil;

    @Override
    public AuthToken authenticate(Auth auth) {

        String email = auth.getEmail();
        String password = auth.getPassword();

        Authentication authentication = userDetailService.authenticate(email, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtil.createToken(authentication);

        AuthToken authToken = new AuthToken();
        authToken.setToken(token);

        return authToken;
    }
}
