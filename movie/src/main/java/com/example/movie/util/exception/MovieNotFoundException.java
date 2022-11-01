package com.example.movie.util.exception;

public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException() {
        super();
    }

    public MovieNotFoundException(String message) {
        super(message);
    }
}
