package com.example.movie.util.exception;

public class APIAccessException extends RuntimeException {
    public APIAccessException() {
    }

    public APIAccessException(String message) {
        super(message);
    }
}
