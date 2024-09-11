package com.pragma.emazon.users_microservice.domain.port.api;

import com.pragma.emazon.users_microservice.domain.model.Auth;
import com.pragma.emazon.users_microservice.domain.model.AuthToken;

public interface IAuthenticationServicePort {

    AuthToken authenticate(Auth auth);
}
