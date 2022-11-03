package com.example.movie.movie.domain;


import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class MovieKeyword {

    @Id @Column(name = "MOVIE_KEYWORD_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "KEYWORD_ID")
    private Keyword keyword;
}
