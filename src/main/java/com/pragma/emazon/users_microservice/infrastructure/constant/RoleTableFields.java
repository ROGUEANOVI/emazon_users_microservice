package com.pragma.emazon.users_microservice.infrastructure.constant;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class RoleTableFields {

    public static final String TABLE_NAME = "role";

    public static final String NAME = "name";

    public static final int MAXIMUM_ROLE_NAME_SIZE = 50;
}
