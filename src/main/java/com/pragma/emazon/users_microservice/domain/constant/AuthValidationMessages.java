package com.pragma.emazon.users_microservice.domain.constant;

public class AuthValidationMessages {

    public static final String INVALID_AUTH_EMAIL_NULL_EMPTY_BLANK = "Authentication email cannot be null, empty or only blank characters";

    public static final String INVALID_AUTH_PASSWORD_NULL_EMPTY_BLANK = "Authentication password cannot be null, empty or only blank characters";

    public static  final String INVALID_CREDENTIALS = "Invalid credentials. Email or password is incorrect";

    private AuthValidationMessages() {}
}
