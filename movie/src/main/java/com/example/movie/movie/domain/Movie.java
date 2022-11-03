package com.example.movie.movie.domain;

import com.example.movie.util.converter.BooleanToYNConverter;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Movie {

    @Id @Column(name = "MOVIE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = BooleanToYNConverter.class)
    private boolean adult;
    private String backdropPath;
    private String belongsToCollection;
    private Integer budget;
    private String homepage;
    private String imdb_id;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private Integer popularity;
    private String posterPath;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private Integer revenue;
    private Integer runtime;
    private String status;
    private String tagline;
    private String title;
    @Convert(converter = BooleanToYNConverter.class)
    private boolean video;
    private Integer voteAverage;
    private Integer voteCount;

    @JoinColumn(name = "PRODUCTION_COMPANY_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductionCompany productionCompanies;

    @JoinColumn(name = "PRODUCTION_COUNTRY_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductionCountry productionCountries;

    @JoinColumn(name = "SPOKEN_LANGUAGE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private SpokenLanguage spokenLanguages;

    @OneToMany(mappedBy = "movie")
    private List<MovieGenre> genres = new ArrayList<>();

    @OneToMany(mappedBy = "movie")
    private Set<MovieKeyword> keywords = new HashSet<>();
}
