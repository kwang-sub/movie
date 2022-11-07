package com.example.movie.movie.repository;

import com.example.movie.movie.domain.Movie;
import com.example.movie.movie.dto.RequestMovieDetailsDTO;
import com.example.movie.trend.dto.TimeWindow;
import com.example.movie.util.pagine.PageRequestDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MovieRepositoryCustom {
    Movie findByIdWithDTO(Long movieId);

    Page<Movie> findPopularList(PageRequestDTO pageRequestDTO);

    Page<Movie> findSimilarList(Long movieId, List<Long> keywordIds, PageRequestDTO pageRequestDTO);

    List<Movie> findTrendList(TimeWindow searchTimeWindow);

}
