package com.pragma.emazon.users_microservice.application.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class AuthSchemaDescriptions {

    public static final String AUTH_REQUEST_DESCRIPTION = "Request body for user authentication";

    public static final String EMAIL_DESCRIPTION = "Email address used for authentication";

    public static final String EMAIL_EXAMPLE = "user@example.com";

    public static final String PASSWORD_DESCRIPTION = "Password used for authentication";

    public static final String PASSWORD_EXAMPLE = "P@ssw0rd123";
}
