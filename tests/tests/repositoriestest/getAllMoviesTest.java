package tests.repositoriestest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import BusinessLayer.Movie;
import DataAccessLayer.MovieRepository;

class getAllMoviesTest {

	@Test
	void getAllMoviesTests() {
		MovieRepository test= new MovieRepository();
		List<Movie>tests=test.getAllMovies();
		assertNotNull(tests);
		
	}

	
}