package com.example.movie.movie.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class MovieGenre {

    @Id
    @Column(name = "MOVIE_GENRE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "GENRE_ID")
    private Genre genre;
}
