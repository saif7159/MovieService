package com.movie.catalogue;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.movie.catalogue.controller.MovieController;
import com.movie.catalogue.service.MovieService;
@WebMvcTest(MovieController.class)
class MovieControllerTest {

	@Autowired
	private MockMvc mock;
	@MockBean
	private MovieService ms;
	@Test
	void getMovieByIdTestApi() throws Exception {
		mock.perform( MockMvcRequestBuilders
			      .get("/getmovie/mid/{id}",1)
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			      .andExpect(status().isOk());
	}

}
