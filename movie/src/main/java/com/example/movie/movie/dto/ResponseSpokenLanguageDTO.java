package com.example.movie.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseSpokenLanguageDTO {
    private String iso_639_1;
    private String name;
}
