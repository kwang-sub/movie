package com.example.movie.util.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResult {
    private Integer status_code;
    private String status_message;
}
