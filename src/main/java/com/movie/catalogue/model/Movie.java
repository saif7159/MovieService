package com.movie.catalogue.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

import com.sun.istack.NotNull;

import io.micrometer.core.lang.NonNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@SuppressWarnings("deprecation")
	@NotEmpty
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

}
