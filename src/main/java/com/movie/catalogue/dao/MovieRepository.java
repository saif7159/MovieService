package com.movie.catalogue.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.movie.catalogue.model.Movie;
@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
public List<Movie> findByMovie(String movie);
public List<Movie> findByDirector(String director);
public List<Movie> findByYear(Integer year);
}
