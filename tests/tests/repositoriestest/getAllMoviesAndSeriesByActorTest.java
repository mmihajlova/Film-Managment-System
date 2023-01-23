package tests.repositoriestest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import BusinessLayer.Movie;
import DataAccessLayer.MovieRepository;

class getAllMoviesAndSeriesByActorTest {

	@Test
	void getAllMoviesAndSeriesByActorTests() {
		MovieRepository test= new MovieRepository();
		List<Movie>tests=test.getAllMoviesAndSeriesByActor("Aaron Paul");
		assertNotNull(tests);
		
	}

}
