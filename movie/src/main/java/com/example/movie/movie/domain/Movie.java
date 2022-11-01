package com.example.movie.movie.domain;

import com.example.movie.util.converter.BooleanToYNConverter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private Integer id;

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

    @OneToMany(mappedBy = "movie")
    private Set<ProductionCompany> productionCompanies = new HashSet<>();
    @OneToMany(mappedBy = "movie")
    private Set<ProductionCountry> productionCountries = new HashSet<>();
    @OneToMany(mappedBy = "movie")
    private Set<SpokenLanguage> spokenLanguages = new HashSet<>();
    @OneToMany(mappedBy = "movie")
    private Set<Genre> genres = new HashSet<>();
}
