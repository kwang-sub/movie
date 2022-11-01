package com.example.movie.movie.repository;

import com.example.movie.movie.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends MovieRepositoryCustom, JpaRepository<Movie, Long> {

}
