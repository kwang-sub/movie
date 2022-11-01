package com.example.movie.movie.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class SpokenLanguage {

    @Id @Column(name = "SPOKEN_LANGUAGE_ID")
    private Integer id;
    private String iso_639_1;
    private String name;

    @JoinColumn(name = "MOVIE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;
}
