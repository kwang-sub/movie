package com.example.movie.movie.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseMovieListDTO {

    private Long id;
    private String posterPath;
    private boolean adult;
    private String overview;
    private String releaseDate;
    private List<Long> genreIds;
    private String originalTitle;
    private String originalLanguage;
    private String title;
    private String backdropPath;
    private Integer popularity;
    private Integer voteCount;
    private boolean video;
    private Integer voteAverage;
}
