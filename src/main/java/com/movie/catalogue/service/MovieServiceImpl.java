package com.movie.catalogue.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.movie.catalogue.dao.MovieRepository;
import com.movie.catalogue.model.Movie;

@Service
public class MovieServiceImpl implements MovieService {
	@Autowired
	private MovieRepository repo;

	@Override
	public Movie createMovie(Movie movie) {
		return repo.saveAndFlush(movie);

	}

	@Override
	@Cacheable(value = "movie", key = "#id")
	public Optional<Movie> getMovie(int id) {
//		try {
//			Thread.sleep(5000L);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return repo.findById(id);
	}

	@Override
	@Cacheable(value = "movie", key = "#movie")
	public List<Movie> getMovieByMovie(String movie) {
		return repo.findByMovie(movie);
	}

	@Override
	@Cacheable(value = "movie", key = "#director")
	public List<Movie> getMovieByDirector(String director) {
		return repo.findByDirector(director);
	}

	@Override
	@Cacheable(value = "movie", key = "#year")
	public List<Movie> getMovieByYear(Integer year) {
		return repo.findByYear(year);
	}

	@Override
	public void updateMovieById(Movie movie) {
		repo.save(movie);
	}

	@Override
	@Cacheable(value = "movie", key = "#all")
	public List<Movie> getAllMovies() {
		return repo.findAll();
	}

	@Override
	public void deleteMovieById(int id) {

		repo.deleteById(id);
	}

}
