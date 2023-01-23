package DataAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BusinessLayer.Movie;
import utils.DBConnection;

public class MovieRepository {
	
	private static MovieRepository instance = null;

	public static MovieRepository getInstance() {

		if (MovieRepository.instance == null) {
			MovieRepository.instance = new MovieRepository();
		}

		return MovieRepository.instance;
	}
	
	public List<Movie> getAllMoviesAndSeries() {
		List<Movie> listOfMovies = new ArrayList<>();
		String query = "SELECT * FROM movies";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet resultSet = ps.executeQuery()) {

			while (resultSet.next()) {
				Movie movie = mapToMovie(resultSet);
				listOfMovies.add(movie);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listOfMovies;
	}
	
	public List<Movie> getAllMovies() {
		List<Movie> listOfMovies = new ArrayList<>();
		String query = "SELECT * FROM movies WHERE MovieOrSeries = 'Movie'";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet resultSet = ps.executeQuery()) {

			while (resultSet.next()) {
				Movie movie = mapToMovie(resultSet);
				listOfMovies.add(movie);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listOfMovies;
	}
	
	public List<Movie> getAllSeries() {
		List<Movie> listOfMovies = new ArrayList<>();
		String query = "SELECT * FROM movies WHERE MovieOrSeries = 'Series'";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet resultSet = ps.executeQuery()) {

			while (resultSet.next()) {
				Movie movie = mapToMovie(resultSet);
				listOfMovies.add(movie);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listOfMovies;
	}
	
	public void insertMovie(String movieName, String movieOrSeries, int yearOfPublishing, String desctiption, String company, int duration, double imdbScore) {
		String query1 = "INSERT INTO movies (MovieName, MovieOrSeries, YearOfPublishing, Description, Company, Duration, IMDB_Score) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DBConnection.getConnection(); 
				PreparedStatement pst1 = conn.prepareStatement(query1)) {
			
			pst1.setString(1, movieName);
			pst1.setString(2, movieOrSeries);
			pst1.setInt(3, yearOfPublishing);
			pst1.setString(4, desctiption);
			pst1.setString(5, company);
			pst1.setInt(6, duration);
			pst1.setDouble(7, imdbScore);
				
			int rs = pst1.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteMovieOrSeriesById(int movieId) {
		String query1 = "DELETE FROM movies WHERE MovieId = ?";
		try (Connection conn = DBConnection.getConnection(); 
				PreparedStatement pst1 = conn.prepareStatement(query1)) {
			
			pst1.setInt(1, movieId);
				
			int rs = pst1.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertSeries(String movieName, String movieOrSeries, int yearOfPublishing, String desctiption, String company, int duration, double imdbScore, int numberOfSeasons) {
		String query1 = "INSERT INTO movies (MovieName, MovieOrSeries, YearOfPublishing, Description, Company, Duration, IMDB_Score, NumberOfSeasons) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DBConnection.getConnection(); 
				PreparedStatement pst1 = conn.prepareStatement(query1)) {
			
			pst1.setString(1, movieName);
			pst1.setString(2, movieOrSeries);
			pst1.setInt(3, yearOfPublishing);
			pst1.setString(4, desctiption);
			pst1.setString(5, company);
			pst1.setInt(6, duration);
			pst1.setDouble(7, imdbScore);
			pst1.setInt(8, numberOfSeasons);
				
			int rs = pst1.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	public List<Movie> getAllMoviesAndSeriesByGenre(String genre) {
		List<Movie> listOfMovies = new ArrayList<>();
		String query = "SELECT * FROM movies JOIN genresmovies ON movies.MovieId = genresmovies.MovieId JOIN genres ON genres.GenreId = genresmovies.GenreId WHERE genres.Genre = ?";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
					
			ps.setString(1, genre);
			
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				Movie movie = mapToMovie(resultSet);
				listOfMovies.add(movie);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listOfMovies;
	}
	
	public List<Movie> getAllMoviesAndSeriesByActor(String actor) {
		List<Movie> listOfMovies = new ArrayList<>();
		String query = "SELECT * FROM movies JOIN actorsmovies ON movies.MovieId = actorsmovies.MovieId JOIN actors ON actors.ActorId = actorsmovies.ActorId WHERE actors.ActorName = ?";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
					
			ps.setString(1, actor);
			
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				Movie movie = mapToMovie(resultSet);
				listOfMovies.add(movie);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listOfMovies;
	}
	
	public List<Movie> getAllFavouriteMoviesAndSeries(int userId) {
		List<Movie> listOfMoviesSeries = new ArrayList<>();
		String query = "SELECT * FROM movies JOIN favourites ON movies.MovieId = favourites.MovieId JOIN users ON favourites.UserId = users.UserId WHERE users.UserId = ?";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
					
			ps.setInt(1, userId);
			
			ResultSet resultSet = ps.executeQuery();
			
			while (resultSet.next()) {
				Movie movie = mapToMovie(resultSet);
				listOfMoviesSeries.add(movie);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listOfMoviesSeries;
	}
	
	public Movie getMovieByMovieName(String movieName) {
		Movie movie = null;
		String query = "SELECT * FROM movies WHERE MovieName = ?";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement ps = getPSWithMovieName(conn, query, movieName);
				ResultSet resultSet = ps.executeQuery()) {
			
			if(resultSet == null) {
				return null;
			}
			else {
				while (resultSet.next()) {
					movie = mapToMovie(resultSet);
				}
				return movie;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return movie;
	}
	
	public Movie getMovieOrSeriesByGenreAndMovieName(String movieName, String genre) {
		Movie movie = null;
		String query = "SELECT * FROM movies JOIN genresmovies ON movies.MovieId = genresmovies.MovieId JOIN genres ON genres.GenreId = genresmovies.GenreId WHERE genres.Genre = ? AND movies.MovieName = ?";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			
			ps.setString(1, genre);
			ps.setString(2, movieName);
			
			ResultSet resultSet = ps.executeQuery();
			
			if(resultSet == null) {
				return null;
			}
			else {
				while (resultSet.next()) {
					movie = mapToMovie(resultSet);
				}
				return movie;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return movie;
	}
	
	public Movie getMovieOrSeriesByActorAndMovieName(String actorName, String movieName) {
		Movie movie = null;
		String query = "SELECT * FROM movies JOIN actorsmovies ON movies.MovieId = actorsmovies.MovieId JOIN actors ON actors.ActorId = actorsmovies.ActorId WHERE actors.ActorName = ? AND movies.MovieName = ?";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(query)) {
			
			ps.setString(1, actorName);
			ps.setString(2, movieName);
			
			ResultSet resultSet = ps.executeQuery();
			
			if(resultSet == null) {
				return null;
			}
			else {
				while (resultSet.next()) {
					movie = mapToMovie(resultSet);
				}
				return movie;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return movie;
	}
	
	private Movie mapToMovie(ResultSet resultSet) throws SQLException {
		int movieId = resultSet.getInt("MovieId");
		String movieName = resultSet.getString("MovieName");
		String movieOrSeries = resultSet.getString("MovieOrSeries");
		int yearOfPublishing = resultSet.getInt("YearOfPublishing");
		String description = resultSet.getString("Description");
		String company = resultSet.getString("Company");
		int duration = resultSet.getInt("Duration");
		double IMDB_score = resultSet.getDouble("IMDB_Score");
		int numberOfSeasons = resultSet.getInt("numberOfSeasons");
		
		Movie movie = new Movie(movieId, movieName, movieOrSeries, 
				yearOfPublishing, description, company, duration, IMDB_score, numberOfSeasons);
		return movie;
	}
	
	
	private PreparedStatement getPSWithMovieName(Connection conn, String query, String movieName) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, movieName);
		return ps;
	}
}
