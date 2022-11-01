package com.example.movie.movie.domain;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
@Getter
public class ProductionCountry {

    @Id @Column(name = "PRODUCTION_COUNTRY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String iso_3166_1;

    @JoinColumn(name = "MOVIE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;
}
