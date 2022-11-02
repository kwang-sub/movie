package com.example.movie.util.pagine;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class PageResultDTO<DTO, EN> {

    private Integer page;
    private Integer total_pages;
    private Integer total_results;
    private List<DTO> results;

    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn, Integer page) {
        this.results = result.stream().map(fn).collect(Collectors.toList());
        this.total_pages = result.getTotalPages();
        this.total_results = results.size();
        this.page = page;
    }
}
