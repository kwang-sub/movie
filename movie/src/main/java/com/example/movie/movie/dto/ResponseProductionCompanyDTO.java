package com.example.movie.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseProductionCompanyDTO {
    private Long id;
    private String name;
    private String logoPath;
    private String originCountry;
}
