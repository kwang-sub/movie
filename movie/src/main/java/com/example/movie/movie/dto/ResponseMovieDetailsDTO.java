package com.example.movie.movie.dto;

import com.example.movie.movie.domain.Genre;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseMovieDetailsDTO {
    private boolean adult;
    private String backdrop_path;
    private Object belongs_to_collection;
    private Integer budget;

    private ResponseGenreDTO genre;
    private String homepage;
    private Integer id;
    private String imdb_id;

    private String original_language;
    private String original_title;
    private String overview;
    private Integer popularity;
    private String poster_path;

    private ResponseProductionCompanyDTO production_companies;
    private ResponseProductionCountryDTO production_countries;
    private String release_date;
    private Integer revenue;
    private Integer runtime;
    private ResponseSpokenLanguageDTO spoken_languages;
    private String status;
    private String tagline;
    private String title;
    private boolean video;
    private Integer vote_average;
    private Integer vote_count;


}
