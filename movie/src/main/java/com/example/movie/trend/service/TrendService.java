package com.example.movie.trend.service;

import java.util.List;

public interface TrendService {
    List<Object> getTrendList(String mediaType, String timeWindow, String apiKey);
}
