package com.example.movie.movie.domain;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class ProductionCountry {

    @Id @Column(name = "PRODUCTION_COUNTRY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String iso_3166_1;

    @OneToMany(mappedBy = "productionCountries")
    private List<Movie> movie = new ArrayList<>();
}
