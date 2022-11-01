package com.example.movie.movie.service;

import com.example.movie.movie.dto.RequestMovieDetailsDTO;
import com.example.movie.movie.dto.ResponseMovieDetailsDTO;

public interface MovieService {

    ResponseMovieDetailsDTO readMovie(Integer movieId, RequestMovieDetailsDTO requestMovieDetailsDTO);
}
