package com.pragma.emazon.users_microservice.infrastructure.exceptionhandler;

import com.pragma.emazon.users_microservice.domain.constant.GlobalMessages;
import com.pragma.emazon.users_microservice.domain.exception.RoleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class RoleExceptionHandler {

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRoleNotFoundException(RoleNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(Collections.singletonMap(GlobalMessages.MESSAGE, ex.getMessage()));
    }
}
