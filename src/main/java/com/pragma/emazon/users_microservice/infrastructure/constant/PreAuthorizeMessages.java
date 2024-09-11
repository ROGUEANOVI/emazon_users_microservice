package com.pragma.emazon.users_microservice.infrastructure.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class PreAuthorizeMessages {

    public static final String DENY_ALL = "denyAll()";

    public static final String PERMIT_ALL = "permitAll()";

    public static final String HAS_ROLE_ADMIN = "hasRole('ROLE_ADMIN')";
}
