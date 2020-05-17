	package com.movie.catalogue.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.movie.catalogue.Exception.ExceptionMessage;
import com.movie.catalogue.Exception.MovieNotFoundException;
import com.movie.catalogue.model.Movie;
import com.movie.catalogue.service.MovieService;

@RestController
@RefreshScope
public class MovieController {
	@Autowired
	private MovieService service;
	@Autowired
	private CacheManager manager;

	@PostMapping("/createmovie")
	public Movie createMovie(@RequestBody Movie movie) {
		manager.getCache("movie").clear();
		return service.createMovie(movie);
		
	}

	@GetMapping("/getmovie/mid/{id}")
	public Optional<Movie> getMovieById(@Valid @PathVariable int id) throws MovieNotFoundException  {
		Optional<Movie> movie = service.getMovie(id);
		//if(movie.isEmpty()) throw new MovieNotFoundException(ExceptionMessage.EMPTY.getMessage()+id);
		return movie;
	}

	@GetMapping("/getmovies")
	public List<Movie> getAllMovies() {
		return service.getAllMovies();
	}

	@GetMapping("/getmovie/mname/{name}")
	public List<Movie> getByMovie(@PathVariable String name) throws MovieNotFoundException {
		List<Movie> movie = service.getMovieByMovie(name);
		if(movie.isEmpty()) throw new MovieNotFoundException(ExceptionMessage.Movie_Name_Not_Found.getMessage() +name);
		return movie;
		
	}

	@GetMapping("/getmovie/dname/{name}")
	public List<Movie> getByDirector(@PathVariable String name) throws MovieNotFoundException {
		List<Movie> movie = service.getMovieByDirector(name);
		if(movie.isEmpty()) throw new MovieNotFoundException(ExceptionMessage.Movie_Director_Not_Found.getMessage() +name);
		return movie;
	}

	@GetMapping("/getmovie/myear/{year}")
	public List<Movie> getByDirector(@PathVariable int year) throws MovieNotFoundException {
		List<Movie> movie = service.getMovieByYear(year);
		if(movie.isEmpty()) throw new MovieNotFoundException(ExceptionMessage.Movie_withyear_Not_Found.getMessage() +year);
		return movie;
	}

	@PutMapping("/updatemovie/{id}")
	public void updateMovieById(@RequestBody Movie movie, @PathVariable int id) {
		Movie update = new Movie(id, movie.getMovie(), movie.getCategory(), movie.getDirector(), movie.getYear(),
				movie.getRating(), movie.getRent(), movie.isAvailable());
		manager.getCache("movie").clear();
		service.updateMovieById(update);
	}

	@DeleteMapping("/removemovie/{id}")
	public void deleteById(@PathVariable int id) throws MovieNotFoundException {
		Optional<Movie> movie = service.getMovie(id);
		if(movie.isEmpty()) throw new MovieNotFoundException(ExceptionMessage.NO_RECORD.getMessage()+id);
		manager.getCache("movie").clear();
		service.deleteMovieById(id);
	}

}
