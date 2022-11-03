package com.example.movie.movie.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseMovieListDTO {

    private Long id;
    private String poster_path;
    private boolean adult;
    private String overview;
    private String release_date;
    private List<Long> genre_ids;
    private String original_title;
    private String original_language;
    private String title;
    private String backdrop_path;
    private Integer popularity;
    private Integer vote_count;
    private boolean video;
    private Integer vote_average;
}
