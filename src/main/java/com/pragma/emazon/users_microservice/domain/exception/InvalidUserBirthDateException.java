package com.pragma.emazon.users_microservice.domain.exception;

public class InvalidUserBirthDateException extends RuntimeException{

    public InvalidUserBirthDateException(String message, String birthDate) {

        super(String.format(message,birthDate));
    }
}
