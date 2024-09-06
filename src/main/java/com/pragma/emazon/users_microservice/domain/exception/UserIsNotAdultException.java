package com.pragma.emazon.users_microservice.domain.exception;

public class UserIsNotAdultException extends RuntimeException {

    public UserIsNotAdultException(String message, String birthDate) {

        super(String.format(message, birthDate));
    }
}
