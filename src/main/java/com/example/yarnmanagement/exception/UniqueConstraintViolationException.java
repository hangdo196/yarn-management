package com.example.yarnmanagement.exception;

public class UniqueConstraintViolationException extends RuntimeException{
    public UniqueConstraintViolationException(String message) {
        super(message);
    }
}
