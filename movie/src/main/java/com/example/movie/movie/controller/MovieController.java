package com.example.movie.movie.controller;

import com.example.movie.movie.dto.RequestMovieDetailsDTO;
import com.example.movie.movie.dto.ResponseMovieDetailsDTO;
import com.example.movie.movie.service.MovieService;
import com.example.movie.util.exception.APIAccessException;
import com.example.movie.util.pagine.PageRequestDTO;
import com.example.movie.util.pagine.PageResultDTO;
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

    @GetMapping(value = "/{movie_id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseMovieDetailsDTO> read(@PathVariable("movie_id") Long movieId, RequestMovieDetailsDTO requestMovieDetailsDTO) {
        if (requestMovieDetailsDTO.getApi_key() == null) {
            throw new APIAccessException("API Key 값을 설정해주세요.");
        }

        ResponseMovieDetailsDTO responseMovieDetailsDTO = movieService.readMovie(movieId, requestMovieDetailsDTO);
        return new ResponseEntity(responseMovieDetailsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/popular", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResultDTO> popularList(@RequestParam(name = "api_key", required = false) String apiKey, PageRequestDTO pageRequestDTO) {
        if (apiKey == null) {
            throw new APIAccessException("API Key 값을 설정해주세요.");
        }
        PageResultDTO resultDTO = movieService.getPopularList(apiKey, pageRequestDTO);
        return new ResponseEntity(resultDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{movie_id}/similar", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResultDTO> similarList(@PathVariable("movie_id") Long movieId, @RequestParam(name = "api_key", required = false) String apiKey,
                                      PageRequestDTO pageRequestDTO) {
        if (apiKey == null) {
            throw new APIAccessException("API Key 값을 설정해주세요.");
        }
        PageResultDTO resultDTO = movieService.getSimilarList(movieId, apiKey, pageRequestDTO);

        return new ResponseEntity(resultDTO, HttpStatus.OK);
    }
}
