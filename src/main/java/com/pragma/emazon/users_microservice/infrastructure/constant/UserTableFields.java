package com.pragma.emazon.users_microservice.infrastructure.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserTableFields {

    public static final String TABLE = "user";

    public static final String FIRST_NAME = "first_name";

    public static final String LAST_NAME = "last_name";

    public static final String DOCUMENT_ID = "document_id";

    public static final String PHONE_NUMBER = "phone_number";

    public static final String BIRTH_DATE = "birth_date";

    public static final String EMAIL = "email";

    public static final String PASSWORD = "password";

    public static final String ROLE_ID = "role_id";
}
