package com.movie.catalogue;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.movie.catalogue.controller.MovieController;

@SpringBootTest
class MovieServiceApplicationTests {
	@Autowired
	private MovieController mc;
	@Test
	void contextLoads() {
		assertThat(mc).isNotNull();
	}

}
