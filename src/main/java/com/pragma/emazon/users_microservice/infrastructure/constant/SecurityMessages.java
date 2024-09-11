package com.pragma.emazon.users_microservice.infrastructure.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class SecurityMessages {

    public static final String AUTHORITIES = "authorities";

    public static final String DELIMITER = ",";

    public static final String BEARER = "Bearer ";

    public static final int JWT_EXPIRATION_IN_MILLISECONDS = 3600000;

    public static final String INVALID_TOKEN = "Invalid token, not authorized";

    public static final String USER_ID = "userId";
}
