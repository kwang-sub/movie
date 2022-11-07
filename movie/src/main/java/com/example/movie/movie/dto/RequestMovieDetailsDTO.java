package com.example.movie.movie.dto;

import lombok.Data;

@Data
public class RequestMovieDetailsDTO {
    private String apiKey;
    private String language;
    private String appendToResponse;
}
