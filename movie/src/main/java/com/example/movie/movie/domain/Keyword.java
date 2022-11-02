package com.example.movie.movie.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Keyword {

    @Id @Column(name = "KEYWORD_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "keyword")
    private List<MovieKeyword> movieKeywords = new ArrayList<>();
}
