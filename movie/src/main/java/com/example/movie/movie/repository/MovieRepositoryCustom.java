package com.example.movie.movie.repository;

import com.example.movie.movie.domain.Movie;
import com.example.movie.movie.dto.RequestMovieDetailsDTO;

public interface MovieRepositoryCustom {
    Movie findByIdWithDTO(Integer movieId, RequestMovieDetailsDTO requestMovieDetailsDTO);
}
