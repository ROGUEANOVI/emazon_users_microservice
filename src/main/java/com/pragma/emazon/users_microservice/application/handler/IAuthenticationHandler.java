package com.pragma.emazon.users_microservice.application.handler;

import com.pragma.emazon.users_microservice.application.dto.request.AuthRequest;
import com.pragma.emazon.users_microservice.application.dto.response.TokenResponse;

public interface IAuthenticationHandler {

    TokenResponse authenticate(AuthRequest authRequest);
}
