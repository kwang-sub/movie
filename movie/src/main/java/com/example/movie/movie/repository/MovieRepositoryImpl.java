package com.example.movie.movie.repository;

import com.example.movie.movie.domain.*;
import com.example.movie.movie.dto.RequestMovieDetailsDTO;
import com.example.movie.util.pagine.PageRequestDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;

import static com.example.movie.movie.domain.QGenre.genre;
import static com.example.movie.movie.domain.QKeyword.keyword;
import static com.example.movie.movie.domain.QMovie.movie;
import static com.example.movie.movie.domain.QMovieGenre.movieGenre;
import static com.example.movie.movie.domain.QMovieKeyword.movieKeyword;
import static com.example.movie.movie.domain.QProductionCompany.productionCompany;
import static com.example.movie.movie.domain.QProductionCountry.productionCountry;
import static com.example.movie.movie.domain.QSpokenLanguage.spokenLanguage;

@RequiredArgsConstructor
public class MovieRepositoryImpl implements MovieRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Movie findByIdWithDTO(Long movieId, RequestMovieDetailsDTO requestMovieDetailsDTO) {
        return queryFactory.selectFrom(movie)
                .leftJoin(movie.genres, movieGenre).fetchJoin()
                .leftJoin(movieGenre.genre, genre).fetchJoin()
                .leftJoin(movie.productionCountries, productionCountry).fetchJoin()
                .leftJoin(movie.productionCompanies, productionCompany).fetchJoin()
                .leftJoin(movie.spokenLanguages, spokenLanguage).fetchJoin()
                .where(movie.id.eq(movieId))
                .fetchOne();
    }

    @Override
    public Page<Movie> findPopularList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable();

        List<Movie> content = queryFactory.selectFrom(movie)
                .join(movie.genres, movieGenre).fetchJoin()
                .join(movieGenre.genre, genre).fetchJoin()
                .where(genre.name.eq(pageRequestDTO.getType()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(movie.popularity.asc(), movie.id.desc())
                .fetch();

        Long count = queryFactory.select(movie.count())
                .from(movie)
                .leftJoin(movie.genres, movieGenre)
                .leftJoin(movieGenre.genre, genre)
                .where(genre.name.eq(pageRequestDTO.getType()))
                .fetchOne();

        return PageableExecutionUtils.getPage(content, pageable, () -> count);
    }

    @Override
    public Page<Movie> findSimilarList(Long movieId, List<Long> keywordIds, PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable();

        List<Movie> content = queryFactory.selectFrom(movie)
                .join(movie.keywords, movieKeyword).fetchJoin()
                .join(movieKeyword.keyword, keyword).fetchJoin()
                .join(movie.genres, movieGenre).fetchJoin()
                .join(movieGenre.genre, genre)
                .where(searchSimilar(movieId, keywordIds))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(movie.id.desc())
                .fetch();

        Long count = queryFactory.select(movie.count())
                .from(movie)
                .join(movie.keywords, movieKeyword)
                .join(movieKeyword.keyword, keyword)
                .where(searchSimilar(movieId, keywordIds))
                .fetchOne();

        return PageableExecutionUtils.getPage(content, pageable, () -> count);
    }

    private Predicate searchSimilar(Long movieId, List<Long> keywordIds) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (keywordIds != null) {
            booleanBuilder.and(keyword.id.in(keywordIds));
        }

        booleanBuilder.and(movie.id.ne(movieId));
        return booleanBuilder;
    }
}
