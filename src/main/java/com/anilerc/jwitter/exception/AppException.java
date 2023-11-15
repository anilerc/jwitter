package com.anilerc.jwitter.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record AppException(String message, HttpStatus httpStatus, ZonedDateTime dateTime){}
