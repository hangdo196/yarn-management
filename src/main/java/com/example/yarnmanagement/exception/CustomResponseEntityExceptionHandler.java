package com.example.yarnmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UniqueConstraintViolationException.class)
    public ResponseEntity<Object> handleUniqueConstraintViolation(UniqueConstraintViolationException ex) {
        return new ResponseEntity<Object>(ex.getMessage(), HttpStatus.CONFLICT);
    }


}
