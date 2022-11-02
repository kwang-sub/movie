package com.example.movie.movie.service;

import com.example.movie.movie.domain.*;
import com.example.movie.movie.dto.*;
import com.example.movie.movie.repository.MovieRepository;
import com.example.movie.util.exception.MovieNotFoundException;
import com.example.movie.util.pagine.PageRequestDTO;
import com.example.movie.util.pagine.PageResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;
    @Override
    public ResponseMovieDetailsDTO readMovie(Integer movieId, RequestMovieDetailsDTO requestMovieDetailsDTO) {
//        API Key 값 유효성 검사하는 로직 후순위 작업(우선 생략)
        Movie movie = movieRepository.findByIdWithDTO(movieId, requestMovieDetailsDTO);
        if (movie == null) {
            throw new MovieNotFoundException("찾을 수 없는 요청입니다.");
        }
        return movieDetailsEntityToDTO(movie);
    }

    @Override
    public PageResultDTO getPopularList(String apiKey, PageRequestDTO pageRequestDTO) {
//        API Key 값 유효성 검사하는 로직 후순위 작업(우선 생략)
        Page<Movie> result = movieRepository.findPopularList(pageRequestDTO);
        System.out.println(result);
        if (result.getContent().size() == 0) {
            throw new MovieNotFoundException("찾을 수 없는 요청입니다.");
        }
        Function<Movie, ResponseMoviePopularDTO> fn = (entity -> ResponseMoviePopularDTO.builder()
                .id(entity.getId())
                .poster_path(entity.getPosterPath())
                .adult(entity.isAdult())
                .overview(entity.getOverview())
                .release_date(entity.getReleaseDate().toString())
                .original_language(entity.getOriginalLanguage())
                .original_title(entity.getOriginalTitle())
                .title(entity.getTitle())
                .backdrop_path(entity.getBackdropPath())
                .popularity(entity.getPopularity())
                .vote_count(entity.getVoteCount())
                .video(entity.isVideo())
                .vote_average(entity.getVoteAverage())
                .genre_id(entity.getGenre().getId())
                .build());

        return new PageResultDTO(result, fn, pageRequestDTO.getPage());
    }

    private List<Integer> genreEntityGetIds(Set<Genre> genres) {
        return genres.stream()
                .map(entity -> entity.getId())
                .collect(Collectors.toList());
    }

    private ResponseMovieDetailsDTO movieDetailsEntityToDTO(Movie movie) {
        return ResponseMovieDetailsDTO.builder()
                .id(movie.getId())
                .adult(movie.isAdult())
                .backdrop_path(movie.getBackdropPath())
                .belongs_to_collection(movie.getBelongsToCollection())
                .budget(movie.getBudget())
                .homepage(movie.getHomepage())
                .imdb_id(movie.getImdb_id())
                .original_language(movie.getOriginalLanguage())
                .original_title(movie.getOriginalTitle())
                .overview(movie.getOverview())
                .popularity(movie.getPopularity())
                .poster_path(movie.getPosterPath())
                .release_date(movie.getReleaseDate().toString())
                .revenue(movie.getRevenue())
                .runtime(movie.getRuntime())
                .status(movie.getStatus())
                .tagline(movie.getTagline())
                .title(movie.getTitle())
                .video(movie.isVideo())
                .vote_average(movie.getVoteAverage())
                .vote_count(movie.getVoteCount())
                .genre(genreEntityToDTO(movie.getGenre()))
                .production_companies(companyEntityToDTO(movie.getProductionCompanies()))
                .production_countries(countryEntityToDTO(movie.getProductionCountries()))
                .spoken_languages(languageEntityToDTO(movie.getSpokenLanguages()))
                .build();
    }

    private ResponseSpokenLanguageDTO languageEntityToDTO(SpokenLanguage spokenLanguages) {
        return new ResponseSpokenLanguageDTO(spokenLanguages.getIso_639_1(), spokenLanguages.getName());
    }

    private ResponseProductionCountryDTO countryEntityToDTO(ProductionCountry productionCountries) {
        return new ResponseProductionCountryDTO(productionCountries.getIso_3166_1(), productionCountries.getName());
    }

    private ResponseProductionCompanyDTO companyEntityToDTO(ProductionCompany productionCompanies) {
        return new ResponseProductionCompanyDTO(productionCompanies.getId(), productionCompanies.getName(),
                productionCompanies.getLogoPath(), productionCompanies.getOriginCountry());
    }

    private ResponseGenreDTO genreEntityToDTO(Genre genres) {
        return new ResponseGenreDTO(genres.getId(), genres.getName());
    }
}