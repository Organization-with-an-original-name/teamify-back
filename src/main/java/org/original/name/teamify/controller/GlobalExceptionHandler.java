package org.original.name.teamify.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<String> handleAccessDeniedException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.I_AM_A_TEAPOT);
    }
}