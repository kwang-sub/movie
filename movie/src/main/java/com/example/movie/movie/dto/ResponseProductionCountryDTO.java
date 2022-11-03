package com.example.movie.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseProductionCountryDTO {
    private String iso_3166_1;
    private String name;
}
