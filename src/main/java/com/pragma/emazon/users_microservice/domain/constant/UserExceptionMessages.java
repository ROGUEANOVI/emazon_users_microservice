package com.pragma.emazon.users_microservice.domain.constant;

public class UserExceptionMessages {

    public static final String USER_WITH_EMAIL_ALREADY_EXISTS = "User with email '%s' already exists";

    public static final String USER_WITH_DOCUMENT_ID_ALREADY_EXISTS = "User with document id '%s' already exists";

    public static final String USER_IS_NOT_ADULT = "User with birth date '%s' is not adult";

    public static final String USER_NOT_FOUND = "User with email '%s' not found";

    public static final String INVALID_USER_BIRTH_DATE_FORMAT = "User birth date '%s' must have a valid date with the format yyyy-MM-dd";

    public static final String INVALID_USER_BIRTH_DATE_IS_IN_THE_FUTURE = "User birth date '%s' cannot be in the future";

    private UserExceptionMessages() {}
}
