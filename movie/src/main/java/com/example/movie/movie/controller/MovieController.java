package com.example.movie.movie.controller;

import com.example.movie.movie.dto.RequestMovieDetailsDTO;
import com.example.movie.movie.dto.ResponseMovieDetailsDTO;
import com.example.movie.movie.service.MovieService;
import com.example.movie.util.exception.APIAccessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping(value = "/{movie_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity read(@PathVariable("movie_id") Integer movieId, RequestMovieDetailsDTO requestMovieDetailsDTO) {
        if (requestMovieDetailsDTO.getApi_key() == null) {
            throw new APIAccessException("API Key 값을 설정해주세요.");
        }

        ResponseMovieDetailsDTO responseMovieDetailsDTO = movieService.readMovie(movieId, requestMovieDetailsDTO);
        return new ResponseEntity(responseMovieDetailsDTO, HttpStatus.OK);
    }
}
