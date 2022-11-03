package com.example.movie.movie.dto;

import lombok.Data;

@Data
public class RequestMovieDetailsDTO {
    private String api_key;
    private String language;
    private String append_to_response;
}
