package com.pragma.emazon.users_microservice.infrastructure.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class AuthApiMessages {

    public static final String TAG_NAME = "Auth";

    public static final String TAG_DESCRIPTION = "Rest controller for authentication";

    public static final String ROUTE_AUTH= "/api/v1/auth";

    public static final String ROUTE_LOGIN = "/login";

    public static final String SUMMARY_LOGIN = "Login";

    public static final String DESCRIPTION_LOGIN = "Rest method for login";

    public static final String DESCRIPTION_200 = "Authentication successful";

    public static final String DESCRIPTION_401 = "Unauthorized. Email or password incorrect";

    public static final String DESCRIPTION_403 = "Access denied. You don't have permission to access this resource";
}
