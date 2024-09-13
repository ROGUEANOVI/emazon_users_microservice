package com.pragma.emazon.users_microservice.application.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserSchemaDescriptions {

    public static final String CREATE_USER_REQUEST_DESCRIPTION = "Request body for creating a new user";

    public static final String FIRST_NAME_DESCRIPTION = "First name of the user";

    public static final String FIRST_NAME_EXAMPLE = "John";

    public static final String LAST_NAME_DESCRIPTION = "Last name of the user";

    public static final String LAST_NAME_EXAMPLE = "Doe";

    public static final String DOCUMENT_ID_DESCRIPTION = "Document ID of the user";

    public static final String DOCUMENT_ID_EXAMPLE = "123456789";

    public static final String PHONE_NUMBER_DESCRIPTION = "Phone number of the user";

    public static final String PHONE_NUMBER_EXAMPLE = "+9876543210";

    public static final String BIRTH_DATE_DESCRIPTION = "Birth date of the user in format yyyy-mm-dd";

    public static final String BIRTH_DATE_EXAMPLE = "1990-01-01";

    public static final String EMAIL_DESCRIPTION = "Email address of the user";

    public static final String EMAIL_EXAMPLE = "user@example.com";

    public static final String PASSWORD_DESCRIPTION = "Password of the user";

    public static final String PASSWORD_EXAMPLE = "P@ssw0rd123";
}
