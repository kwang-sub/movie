package com.example.movie.movie.service;

import com.example.movie.movie.dto.RequestMovieDetailsDTO;
import com.example.movie.movie.dto.ResponseMovieDetailsDTO;
import com.example.movie.util.pagine.PageRequestDTO;
import com.example.movie.util.pagine.PageResultDTO;

public interface MovieService {

    ResponseMovieDetailsDTO readMovie(Long movieId, RequestMovieDetailsDTO requestMovieDetailsDTO);

    PageResultDTO getPopularList(String apiKey, PageRequestDTO pageRequestDTO);

    PageResultDTO getSimilarList(Long movieId, String apiKey, PageRequestDTO pageRequestDTO);
}
