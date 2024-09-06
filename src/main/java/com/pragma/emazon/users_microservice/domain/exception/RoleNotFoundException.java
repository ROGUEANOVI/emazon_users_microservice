package com.pragma.emazon.users_microservice.domain.exception;

public class RoleNotFoundException extends RuntimeException {

    public RoleNotFoundException(String message, String name) {

        super(String.format(message, name));
    }
}
