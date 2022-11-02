package com.example.movie.movie.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class SpokenLanguage {

    @Id @Column(name = "SPOKEN_LANGUAGE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String iso_639_1;
    private String name;

    @OneToMany(mappedBy = "spokenLanguages")
    private List<Movie> movie;
}
