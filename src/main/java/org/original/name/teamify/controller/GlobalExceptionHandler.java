package org.original.name.teamify.controller;

import org.original.name.teamify.exception.BadRequestExcepton;
import org.original.name.teamify.exception.ResourceNotFoundException;
import org.original.name.teamify.exception.RestrictedResourceException;
import org.original.name.teamify.exception.UnauthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({UnauthenticatedException.class})
    public ResponseEntity<Void> handleUnauthorized(UnauthenticatedException e){
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({BadRequestExcepton.class})
    public ResponseEntity<String> handleBadRequest(BadRequestExcepton excepton){
        return new ResponseEntity<>(excepton.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Void> handleNotFound(ResourceNotFoundException e){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({RestrictedResourceException.class})
    public ResponseEntity<Void> handleForbidden(RestrictedResourceException e){
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.I_AM_A_TEAPOT);
    }

}
