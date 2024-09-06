package com.pragma.emazon.users_microservice.domain.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message, String field) {

        super(String.format(message, field));
    }
}
