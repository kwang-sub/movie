package com.example.movie.trend.controller;

import com.example.movie.util.exception.APIAccessException;
import com.example.movie.util.exception.BadParamException;
import com.example.movie.util.exception.EntityNotFoundException;
import com.example.movie.util.response.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(assignableTypes = {TrendController.class})
public class TrendControllerAdvice {

    @ExceptionHandler(APIAccessException.class)
    public ResponseEntity<ErrorResult> APIAccessExHandler(APIAccessException e) {
        log.error("[exceptionHandler] ", e);
        ErrorResult errorResult = new ErrorResult(401, e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResult> TrendNotFoundExHandler(EntityNotFoundException e) {
        log.error("[exceptionHandler] ", e);
        ErrorResult errorResult = new ErrorResult(404, e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadParamException.class)
    public ResponseEntity<ErrorResult> BadParamExHandler(BadParamException e) {
        log.error("[exceptionHandler] ", e);
        ErrorResult errorResult = new ErrorResult(404, e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }
}
