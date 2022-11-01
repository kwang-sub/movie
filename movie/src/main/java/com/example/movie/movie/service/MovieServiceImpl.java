package com.example.movie.movie.service;

import com.example.movie.movie.domain.*;
import com.example.movie.movie.dto.*;
import com.example.movie.movie.repository.MovieRepository;
import com.example.movie.util.exception.MovieNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
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
            throw new MovieNotFoundException("찾을 수 없는 요청 값입니다.");
        }
        return movieDetailsEntityToDTO(movie);
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
                .genres(genresEntityToDTO(movie.getGenres()))
                .production_companies(companyEntityToDTO(movie.getProductionCompanies()))
                .production_countries(countryEntityToDTO(movie.getProductionCountries()))
                .spoken_languages(languageEntityToDTO(movie.getSpokenLanguages()))
                .build();
    }

    private List<ResponseSpokenLanguageDTO> languageEntityToDTO(Set<SpokenLanguage> spokenLanguages) {
        return spokenLanguages.stream()
                .map(entity -> new ResponseSpokenLanguageDTO(entity.getIso_639_1(), entity.getName()))
                .collect(Collectors.toList());
    }

    private List<ResponseProductionCountryDTO> countryEntityToDTO(Set<ProductionCountry> productionCountries) {
        return productionCountries.stream()
                .map(entity -> new ResponseProductionCountryDTO(entity.getIso_3166_1(), entity.getName()))
                .collect(Collectors.toList());
    }

    private List<ResponseProductionCompanyDTO> companyEntityToDTO(Set<ProductionCompany> productionCompanies) {
        return productionCompanies.stream()
                .map(entity -> new ResponseProductionCompanyDTO(entity.getId(), entity.getName(),
                        entity.getLogoPath(), entity.getOriginCountry()))
                .collect(Collectors.toList());
    }

    private List<ResponseGenreDTO> genresEntityToDTO(Set<Genre> genres) {
        return genres.stream()
                .map(entity -> new ResponseGenreDTO(entity.getId(), entity.getName()))
                .collect(Collectors.toList());
    }
}
