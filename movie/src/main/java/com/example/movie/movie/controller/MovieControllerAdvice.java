package com.example.movie.movie.controller;

import com.example.movie.util.exception.APIAccessException;
import com.example.movie.util.exception.MovieNotFoundException;
import com.example.movie.util.response.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(assignableTypes = {MovieController.class})
public class MovieControllerAdvice {

    @ExceptionHandler(APIAccessException.class)
    public ResponseEntity<ErrorResult> APIAccessExHandler(APIAccessException e) {
        log.error("[exceptionHandler] ", e);
        ErrorResult errorResult = new ErrorResult(401, e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<ErrorResult> MovieNotFoundExHandler(MovieNotFoundException e) {
        log.error("[exceptionHandler] ", e);
        ErrorResult errorResult = new ErrorResult(404, e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.NOT_FOUND);
    }
}
