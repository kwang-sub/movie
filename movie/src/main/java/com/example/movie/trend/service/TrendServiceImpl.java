package com.example.movie.trend.service;

import com.example.movie.movie.domain.Movie;
import com.example.movie.movie.domain.MovieGenre;
import com.example.movie.movie.dto.ResponseMovieListDTO;
import com.example.movie.movie.repository.MovieRepository;
import com.example.movie.trend.dto.TimeWindow;
import com.example.movie.util.exception.BadParamException;
import com.example.movie.util.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TrendServiceImpl implements TrendService{

    private final MovieRepository movieRepository;

    @Override
    public List<Object> getTrendList(String mediaType, String timeWindow, String apiKey) {
//        apiKey 유효성 검사하는 로직 추가해야됨
        TimeWindow searchTimeWindow = new TimeWindow(timeWindow);
        List list = null;
        if (mediaType.equals("movie")) {
            List<Movie> trendList = movieRepository.findTrendList(searchTimeWindow);
            if (trendList.size() == 0) {
                throw new EntityNotFoundException("해당 기간에 조회되는 영화가 없습니다.");
            }
            list = movieEntityToDTO(trendList);
        } else {
            throw new BadParamException("지원하지 않는 미디어타입입니다.");
        }

        return list;
    }

    private List movieEntityToDTO(List<Movie> trendList) {
        return trendList.stream()
                .map(entity -> ResponseMovieListDTO.builder()
                        .id(entity.getId())
                        .poster_path(entity.getPosterPath())
                        .adult(entity.isAdult())
                        .overview(entity.getOverview())
                        .release_date(entity.getReleaseDate().toString())
                        .genre_ids(entityToMovieGenreIds(entity.getGenres()))
                        .original_title(entity.getOriginalTitle())
                        .original_language(entity.getOriginalLanguage())
                        .title(entity.getTitle())
                        .backdrop_path(entity.getBackdropPath())
                        .popularity(entity.getPopularity())
                        .vote_count(entity.getVoteCount())
                        .video(entity.isVideo())
                        .vote_average(entity.getVoteAverage())
                        .build())
                .collect(Collectors.toList());
    }

    private List<Long> entityToMovieGenreIds(List<MovieGenre> genres) {
        return genres.stream()
                .map(entity -> entity.getGenre().getId())
                .collect(Collectors.toList());
    }
}
