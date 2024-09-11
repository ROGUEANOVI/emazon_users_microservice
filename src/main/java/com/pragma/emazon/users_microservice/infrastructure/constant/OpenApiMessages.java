package com.pragma.emazon.users_microservice.infrastructure.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class OpenApiMessages {

    public static final  String SECURITY_SCHEME_KEY = "bearerAuth";

    public static final  String SECURITY_SCHEME = "bearer";

    public static final String BEARER_FORMAT = "JWT";

    public static final String TITLE = "Emazon Users Management API";

    public static final String VERSION = "1.0.0";

    public static final String DESCRIPTION = "This is the Users Management Microservice for the Emazon application." +
                                             "It provides endpoints for managing users.";

    public static final String CONTACT_NAME = "Ovidio Romero";

    public static final String CONTACT_EMAIL = "ovidioromero66@gmail.com";

    public static final String CONTACT_URL = "https://www.linkedin.com/in/ovidio-romero/";

    public static final String REPOSITORY_URL = "https://github.com/ROGUEANOVI/emazon_users_microservice";

    public static final String EXTERNAL_DOCUMENT_DESCRIPTION = "Emazon Users Management API GitHub Repository";

    public static final String CODE_200 = "200";

    public static final String CODE_201 = "201";

    public static final String CODE_400 = "400";

    public static final String CODE_401 = "401";

    public static final String CODE_403 = "403";

    public static final String CODE_409 = "409";
}
