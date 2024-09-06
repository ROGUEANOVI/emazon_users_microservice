package com.pragma.emazon.users_microservice.infrastructure.exceptionhandler;

import com.pragma.emazon.users_microservice.domain.constant.GlobalMessages;
import com.pragma.emazon.users_microservice.domain.exception.InvalidUserBirthDateException;
import com.pragma.emazon.users_microservice.domain.exception.UserAlreadyExistsException;
import com.pragma.emazon.users_microservice.domain.exception.UserIsNotAdultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserIsNotAdultException.class)
    public ResponseEntity<Map<String, String>> handleUserIsNotAdultException(UserIsNotAdultException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(GlobalMessages.MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(InvalidUserBirthDateException.class)
    public ResponseEntity<Map<String, String>> handleUserIsNotAdultException(InvalidUserBirthDateException ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(GlobalMessages.MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(GlobalMessages.MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUsernameNotFoundException(UsernameNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(GlobalMessages.MESSAGE, ex.getMessage()));
    }
}
