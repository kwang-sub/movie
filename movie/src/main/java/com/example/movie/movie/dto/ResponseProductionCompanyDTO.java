package com.example.movie.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseProductionCompanyDTO {
    private Long id;
    private String name;
    private String logo_path;
    private String origin_country;
}
