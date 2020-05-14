package com.movie.catalogue.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor

public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String movie;
	private String category;
	private String director;
	private int year;
	private float rating;
	private float rent;

	public Movie(int id, String movie, String category, String director, int year, float rating, float rent) {
		super();
		this.id = id;
		this.movie = movie;
		this.category = category;
		this.director = director;
		this.year = year;
		this.rating = rating;
		this.rent = rent;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public float getRent() {
		return rent;
	}

	public void setRent(float rent) {
		this.rent = rent;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", movie=" + movie + ", category=" + category + ", director=" + director + ", year="
				+ year + ", rating=" + rating + ", rent=" + rent + "]";
	}
	
	
	
	
	

}
