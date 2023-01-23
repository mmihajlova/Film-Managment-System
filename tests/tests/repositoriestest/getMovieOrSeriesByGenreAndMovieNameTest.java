package tests.repositoriestest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import BusinessLayer.Movie;
import DataAccessLayer.MovieRepository;

class getMovieOrSeriesByGenreAndMovieNameTest {

	@Test
	void getMovieOrSeriesByGenreAndMovieNameTests() {
		MovieRepository test= new MovieRepository();
		Movie tests=test.getMovieOrSeriesByGenreAndMovieName("Rocky","Action");
		assertNotNull(tests);
		
	}

	
}