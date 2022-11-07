package com.example.movie.movie.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseMovieDetailsDTO {
    private boolean adult;
    private String backdropPath;
    private Object belongsToCollection;
    private Integer budget;

    private List<ResponseGenreDTO> genre;
    private String homepage;
    private Long id;
    private String imdbId;

    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private Integer popularity;
    private String posterPath;

    private ResponseProductionCompanyDTO productionCompanies;
    private ResponseProductionCountryDTO productionCountries;
    private String releaseDate;
    private Integer revenue;
    private Integer runtime;
    private ResponseSpokenLanguageDTO spokenLanguages;
    private String status;
    private String tagline;
    private String title;
    private boolean video;
    private Integer voteAverage;
    private Integer voteCount;


}
