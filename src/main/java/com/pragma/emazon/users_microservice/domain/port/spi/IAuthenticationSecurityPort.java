package com.pragma.emazon.users_microservice.domain.port.spi;

import com.pragma.emazon.users_microservice.domain.model.Auth;
import com.pragma.emazon.users_microservice.domain.model.AuthToken;

public interface IAuthenticationSecurityPort {

    AuthToken authenticate(Auth auth);
}
