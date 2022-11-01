package com.example.movie.movie.repository;

import com.example.movie.movie.domain.*;
import com.example.movie.movie.dto.RequestMovieDetailsDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.example.movie.movie.domain.QGenre.genre;
import static com.example.movie.movie.domain.QMovie.movie;
import static com.example.movie.movie.domain.QProductionCompany.productionCompany;
import static com.example.movie.movie.domain.QProductionCountry.productionCountry;
import static com.example.movie.movie.domain.QSpokenLanguage.spokenLanguage;

@RequiredArgsConstructor
public class MovieRepositoryImpl implements MovieRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Movie findByIdWithDTO(Integer movieId, RequestMovieDetailsDTO requestMovieDetailsDTO) {

        return queryFactory.selectFrom(movie)
                .leftJoin(movie.genres, genre).fetchJoin()
                .leftJoin(movie.productionCountries, productionCountry).fetchJoin()
                .leftJoin(movie.productionCompanies, productionCompany).fetchJoin()
                .leftJoin(movie.spokenLanguages, spokenLanguage).fetchJoin()
                .where(movie.id.eq(movieId))
                .fetchOne();
    }
}
