package com.pragma.emazon.users_microservice.infrastructure.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserApiMessages {

    public static final String TAG_NAME = "User";

    public static final String TAG_DESCRIPTION = "Rest controller for users";

    public static final String ROUTE_USERS = "/api/v1/users";

    public static final String ROUTE_WAREHOUSE_ASSISTANTS = "/warehouse-assistants";

    public static final String ROUTE_CUSTOMERS = "/customers";

    public static final String SUMMARY_CREATE_WAREHOUSE_ASSISTANT_USER = "Create new user with role warehouse assistant";

    public static final String DESCRIPTION_CREATE_WAREHOUSE_ASSISTANT_USER = "Rest method for create new user with role warehouse assistant";

    public static final String SUMMARY_CREATE_CUSTOMER_USER = "Create new user with role customer";

    public static final String DESCRIPTION_CREATE_CUSTOMER_USER = "Rest method for create new user with role customer";

    public static final String DESCRIPTION_201 = "User created";

    public static final String DESCRIPTION_400 = "User bad request";

    public static final String DESCRIPTION_409 = "User already exists";
}
