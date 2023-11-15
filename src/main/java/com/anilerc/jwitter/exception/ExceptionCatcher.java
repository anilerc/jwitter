package com.anilerc.jwitter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionCatcher {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e) {
        var appException = new AppException(e.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());

        return new ResponseEntity<>(appException, appException.httpStatus());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidArgumentsException(MethodArgumentNotValidException e) {
        var appException = new AppException("Validation failed for the request.", HttpStatus.BAD_REQUEST, ZonedDateTime.now());

        return new ResponseEntity<>(appException, appException.httpStatus());
    }
}