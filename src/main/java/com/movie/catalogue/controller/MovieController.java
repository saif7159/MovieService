package com.movie.catalogue.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.movie.catalogue.model.Movie;
import com.movie.catalogue.service.MovieService;

@RestController
@RefreshScope
public class MovieController {
	@Autowired
	private MovieService service;

	@PostMapping("/createmovie")
	public Movie createMovie(@RequestBody Movie movie) {
		return service.createMovie(movie);
	}

	@GetMapping("/getmovie/mid/{id}")
	public Optional<Movie> getMovieById(@PathVariable int id) {
		return service.getMovie(id);
	}

	@GetMapping("/getmovies")
	public List<Movie> getAllMovies() {
		return service.getAllMovies();
	}

	@GetMapping("/getmovie/mname/{name}")
	public List<Movie> getByMovie(@PathVariable String name) {
		return service.getMovieByMovie(name);
	}

	@GetMapping("/getmovie/dname/{name}")
	public List<Movie> getByDirector(@PathVariable String name) {
		return service.getMovieByDirector(name);
	}

	@GetMapping("/getmovie/myear/{year}")
	public List<Movie> getByDirector(@PathVariable int year) {
		return service.getMovieByYear(year);
	}

	@PutMapping("/updatemovie/{id}")
	public void updateMovieById(@RequestBody Movie movie, @PathVariable int id) {
		Movie update = new Movie(id, movie.getMovie(), movie.getCategory(), movie.getDirector(), movie.getYear(),
				movie.getRating(), movie.getRent());
		service.updateMovieById(update);
	}

	@DeleteMapping("/removemovie/{id}")
	public void deleteById(@PathVariable int id) {
		service.deleteMovieById(id);
	}

}
