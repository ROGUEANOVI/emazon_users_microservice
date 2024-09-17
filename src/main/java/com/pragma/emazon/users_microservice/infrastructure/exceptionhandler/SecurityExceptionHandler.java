package com.pragma.emazon.users_microservice.infrastructure.exceptionhandler;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.pragma.emazon.users_microservice.domain.constant.GlobalMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class SecurityExceptionHandler {

    @ExceptionHandler(JWTVerificationException.class)
    public ResponseEntity<Map<String, String>> handleJWTVerificationException(JWTVerificationException ex) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of(GlobalMessages.MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleBadCredentialsException(BadCredentialsException ex) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(GlobalMessages.MESSAGE, ex.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, String>> handleAccessDeniedException(AccessDeniedException ex) {

        return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of(GlobalMessages.MESSAGE, ex.getMessage()));
    }
}
