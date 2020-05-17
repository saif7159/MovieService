package com.movie.catalogue;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.movie.catalogue.model.Movie;
import com.movie.catalogue.service.MovieService;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MovieService ms;

	@Test
	public void testCreateMovie() throws Exception {

		Movie movie = new Movie(1, "dhoom", "action", "karan", 2000, 4.0f, 20.0f, true);

		Mockito.when(ms.createMovie(movie)).thenReturn(movie);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(movie);

		System.out.println(requestJson);

		mockMvc.perform(post("/createmovie").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(requestJson)).andDo(print()).andExpect(status().isOk());

	}

	@Test
	public void testGetMovieById() throws Exception {

		Mockito.when(ms.getMovie(Mockito.anyInt()))
				.thenReturn(Optional.of(new Movie(1, "dhoom", "action", "karan", 2000, 4.0f, 20.0f, true)));
		mockMvc.perform(get("/getmovie/mid/1").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(jsonPath("$.id", is(1)));
	}

	@Test
	public void testGetAllMovies() throws Exception {

		Mockito.when(ms.getAllMovies())
				.thenReturn(Arrays.asList(new Movie(1, "dhoom", "action", "karan", 2000, 4.0f, 20.0f, true),
						new Movie(2, "Taarzan", "action", "karan", 2000, 4.0f, 20.0f, true)));

		mockMvc.perform(get("/getmovies").accept(MediaType.APPLICATION_JSON))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(jsonPath("$[0].movie", is("dhoom"))).andExpect(jsonPath("$[1].movie", is("Taarzan")))
				.andExpect(jsonPath("$[0].director", is("karan"))).andExpect(jsonPath("$[1].director", is("karan")))
				.andExpect(jsonPath("$[0].category", is("action"))).andExpect(jsonPath("$[1].category", is("action")))
				.andExpect(jsonPath("$[0].year", is(2000))).andExpect(jsonPath("$[1].year", is(2000)));

	}

	@Test
	public void testGetByMovie() throws Exception {
		Mockito.when(ms.getMovieByMovie(Mockito.anyString()))
				.thenReturn(Arrays.asList(new Movie(1, "dhoom", "action", "karan", 2000, 4.0f, 20.0f, true),
						new Movie(2, "dhoom", "action", "karan", 2001, 4.0f, 20.0f, true)));

		mockMvc.perform(get("/getmovie/mname/dhoom").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$[1].movie", is("dhoom")));

	}

}