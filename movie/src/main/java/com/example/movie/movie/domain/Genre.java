package com.example.movie.movie.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Genre {

    @Id @Column(name = "GENRE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @JoinColumn(name = "MOVIE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;
}
