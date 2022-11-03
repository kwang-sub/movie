package com.example.movie.trend.controller;

import com.example.movie.trend.service.TrendService;
import com.example.movie.util.exception.APIAccessException;
import com.example.movie.util.exception.BadParamException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trending")
@RequiredArgsConstructor
public class TrendController {

    private final TrendService trendService;

    @GetMapping(value = "/{media_type}/{time_window}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity trendList(@PathVariable("media_type") String mediaType,
                                    @PathVariable("time_window") String timeWindow, @RequestParam(value = "api_key", required = false) String apiKey) {
        if (apiKey == null) {
            throw new APIAccessException("API Key 값을 설정해주세요.");
        }
        if (!trendParamCheck(mediaType, timeWindow)) {
            throw new BadParamException("올바르지 않은 요청 값입니다");
        }

        List<Object> trendList = trendService.getTrendList(mediaType, timeWindow, apiKey);

        return new ResponseEntity(trendList, HttpStatus.OK);
    }

    private boolean trendParamCheck(String mediaType, String timeWindow) {
        List<String> mediaTypes = List.of("all", "movie", "tv", "person");
        List<String> timeWindows = List.of("day", "week");
        return mediaTypes.contains(mediaType) && timeWindows.contains(timeWindow);
    }
}
