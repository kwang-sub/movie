package com.example.movie.movie.service;

import com.example.movie.movie.domain.*;
import com.example.movie.movie.dto.*;
import com.example.movie.movie.repository.MovieRepository;
import com.example.movie.util.constant.ErrorMessage;
import com.example.movie.util.exception.EntityNotFoundException;
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
public class MovieService {

    private final MovieRepository movieRepository;
    public ResponseMovieDetailsDTO readMovie(Long movieId, String apiKey) {
//        API Key 값 유효성 검사하는 로직 후순위 작업(우선 생략)
        Movie movie = movieRepository.findByIdWithDTO(movieId);
        if (movie == null) {
            throw new EntityNotFoundException(ErrorMessage.ENTITY_NOT_FOUND);
        }
        return movieDetailsEntityToDTO(movie);
    }

    public PageResultDTO getPopularList(String apiKey, PageRequestDTO pageRequestDTO) {
//        API Key 값 유효성 검사하는 로직 후순위 작업(우선 생략)
        Page<Movie> result = movieRepository.findPopularList(pageRequestDTO);
        if (result.getContent().size() == 0) {
            throw new EntityNotFoundException(ErrorMessage.ENTITY_NOT_FOUND);
        }
        Function<Movie, ResponseMovieListDTO> fn = (entity -> ResponseMovieListDTO.builder()
                .id(entity.getId())
                .posterPath(entity.getPosterPath())
                .adult(entity.isAdult())
                .overview(entity.getOverview())
                .releaseDate(entity.getReleaseDate().toString())
                .originalLanguage(entity.getOriginalLanguage())
                .originalTitle(entity.getOriginalTitle())
                .title(entity.getTitle())
                .backdropPath(entity.getBackdropPath())
                .popularity(entity.getPopularity())
                .voteCount(entity.getVoteCount())
                .video(entity.isVideo())
                .voteAverage(entity.getVoteAverage())
                .genreIds(entityToMovieGenreIds(entity.getGenres()))
                .build());
        return new PageResultDTO(result, fn, pageRequestDTO.getPage());
    }

    public PageResultDTO getSimilarList(Long movieId, String apiKey, PageRequestDTO pageRequestDTO) {
//        API Key 값 유효성 검사하는 로직 후순위 작업(우선 생략)
        
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorMessage.ENTITY_NOT_FOUND));
        List<Long> keywordIds = entityToMovieKeywordIds(movie.getKeywords());

        Page<Movie> result = movieRepository.findSimilarList(movieId, keywordIds, pageRequestDTO);
        if (result.getContent().size() == 0) {
            throw new EntityNotFoundException(ErrorMessage.ENTITY_NOT_FOUND);
        }
        Function<Movie, ResponseMovieListDTO> fn = (entity -> ResponseMovieListDTO.builder()
                .id(entity.getId())
                .posterPath(entity.getPosterPath())
                .adult(entity.isAdult())
                .overview(entity.getOverview())
                .releaseDate(entity.getReleaseDate().toString())
                .genreIds(entityToMovieGenreIds(entity.getGenres()))
                .originalTitle(entity.getOriginalTitle())
                .originalLanguage(entity.getOriginalLanguage())
                .title(entity.getTitle())
                .backdropPath(entity.getBackdropPath())
                .popularity(entity.getPopularity())
                .voteCount(entity.getVoteCount())
                .video(entity.isVideo())
                .voteAverage(entity.getVoteAverage())
                .build());

        return new PageResultDTO(result, fn, pageRequestDTO.getPage());
    }

    private List<Long> entityToMovieKeywordIds(Set<MovieKeyword> keywords) {
        return keywords.stream()
                .map(entity -> entity.getKeyword().getId())
                .collect(Collectors.toList());
    }

    private List<Long> entityToMovieGenreIds(List<MovieGenre> genres) {
        return genres.stream()
                .map(entity -> entity.getGenre().getId())
                .collect(Collectors.toList());
    }

    private ResponseMovieDetailsDTO movieDetailsEntityToDTO(Movie movie) {
        return ResponseMovieDetailsDTO.builder()
                .id(movie.getId())
                .adult(movie.isAdult())
                .backdropPath(movie.getBackdropPath())
                .belongsToCollection(movie.getBelongsToCollection())
                .budget(movie.getBudget())
                .homepage(movie.getHomepage())
                .imdbId(movie.getImdb_id())
                .originalLanguage(movie.getOriginalLanguage())
                .originalTitle(movie.getOriginalTitle())
                .overview(movie.getOverview())
                .popularity(movie.getPopularity())
                .posterPath(movie.getPosterPath())
                .releaseDate(movie.getReleaseDate().toString())
                .revenue(movie.getRevenue())
                .runtime(movie.getRuntime())
                .status(movie.getStatus())
                .tagline(movie.getTagline())
                .title(movie.getTitle())
                .video(movie.isVideo())
                .voteAverage(movie.getVoteAverage())
                .voteCount(movie.getVoteCount())
                .genre(genreEntityToDTO(movie.getGenres()))
                .productionCompanies(companyEntityToDTO(movie.getProductionCompanies()))
                .productionCountries(countryEntityToDTO(movie.getProductionCountries()))
                .spokenLanguages(languageEntityToDTO(movie.getSpokenLanguages()))
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

    private List<ResponseGenreDTO> genreEntityToDTO(List<MovieGenre> genres) {
        return genres.stream()
                .map(entity -> new ResponseGenreDTO(entity.getGenre().getId(), entity.getGenre().getName()))
                .collect(Collectors.toList());
    }
}
