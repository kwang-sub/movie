package com.example.movie.movie.repository;

import com.example.movie.movie.domain.Movie;
import com.example.movie.movie.dto.RequestMovieDetailsDTO;
import com.example.movie.util.pagine.PageRequestDTO;
import org.springframework.data.domain.Page;

public interface MovieRepositoryCustom {
    Movie findByIdWithDTO(Integer movieId, RequestMovieDetailsDTO requestMovieDetailsDTO);

    Page<Movie> findPopularList(PageRequestDTO pageRequestDTO);
}
