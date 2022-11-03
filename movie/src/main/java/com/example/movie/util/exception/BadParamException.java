package com.example.movie.util.exception;

public class BadParamException extends RuntimeException{
    public BadParamException() {
    }

    public BadParamException(String message) {
        super(message);
    }
}
