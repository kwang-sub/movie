package com.example.movie.movie.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class ProductionCompany {

    @Id @Column(name = "PRODUCTION_COMPANY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String logoPath;
    private String originCountry;

    @OneToMany(mappedBy = "productionCompanies")
    private List<Movie> movie = new ArrayList<>();
}
