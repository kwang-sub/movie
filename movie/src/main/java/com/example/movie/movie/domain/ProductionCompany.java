package com.example.movie.movie.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class ProductionCompany {

    @Id @Column(name = "PRODUCTION_COMPANY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String logoPath;
    private String originCountry;

    @JoinColumn(name = "MOVIE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;
}
