package com.example.movie.movie.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Genre {

    @Id @Column(name = "GENRE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "genre")
    private List<MovieGenre> movieGenreList = new ArrayList<>();
}
