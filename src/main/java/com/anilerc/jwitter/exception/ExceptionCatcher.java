package com.anilerc.jwitter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionCatcher {


    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
        var appException = new AppException(e.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());

        return new ResponseEntity<>(appException, appException.httpStatus());
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorizedOperation(UnauthorizedException e) {
        var appException = new AppException(e.getMessage(), HttpStatus.UNAUTHORIZED, ZonedDateTime.now());

        return new ResponseEntity<>(appException, appException.httpStatus());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidArgumentsException(MethodArgumentNotValidException e) {
        var appException = new AppException("Validation failed for the request.", HttpStatus.BAD_REQUEST, ZonedDateTime.now());

        return new ResponseEntity<>(appException, appException.httpStatus());
    }
}