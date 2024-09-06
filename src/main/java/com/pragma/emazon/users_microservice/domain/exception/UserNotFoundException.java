package com.pragma.emazon.users_microservice.domain.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserNotFoundException extends UsernameNotFoundException {

    public UserNotFoundException(String message, String email) {

        super(String.format(message, email));
    }
}
