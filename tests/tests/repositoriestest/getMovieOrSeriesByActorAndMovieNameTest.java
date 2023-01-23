package tests.repositoriestest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import BusinessLayer.Movie;
import DataAccessLayer.MovieRepository;

class getMovieOrSeriesByActorAndMovieNameTest {

	@Test
	void getMovieOrSeriesByActorAndMovieNameTests() {
		MovieRepository test= new MovieRepository();
		Movie tests=test.getMovieOrSeriesByActorAndMovieName("Sylvester Stallone","Rocky");
		assertNotNull(tests);
		
	}

	
}