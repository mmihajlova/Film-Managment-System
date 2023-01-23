package PresentationLayer;

import menus.ErrorMenu;
import menus.LoggedUserMenu;
import utils.ConsoleUtils;

import services.AuthenticationService;

public class LoggedUserManagementController {
	
	private final AuthenticationService authenticationService;
	
	public LoggedUserManagementController() {
		this.authenticationService = AuthenticationService.getInstance();
		
	}

	public void run() {
		
		LoggedUserMenu.loggedUser();
		
		int choice = ConsoleUtils.readInteger();
		
		while(true) {
			switch (choice) {
				case 1: {
					viewAllMovies();
					break;
				}
				case 2: {
					viewAllSeries();
					break;
				}
				case 3: {
					viewAllFavourites();
					break;
				}
				case 4: {
					viewAllMoviesOrSeriesByGenre();
					break;
				}
				case 5: {
					viewAllMoviesOrSeriesByActor();
					break;
				}
				case 6: {
					viewAllActors();
					break;
				}
				case 7: {
					deletePersonalAccountById();
					break;
				}
				case 8: {
					authenticationService.destroySession();
					backToMainMenu();
					break;
				}
				default:
					ErrorMenu.invalidInputError();
					choice = ConsoleUtils.readInteger();
					break;
			}
		}
	}
	
	private void viewAllMovies() {
		MovieController viewAllMoviesController = new MovieController();
		viewAllMoviesController.viewAllMovies();
    }
	
	private void viewAllSeries() {
		MovieController viewAllSeriesController = new MovieController();
		viewAllSeriesController.viewAllSeries();
    }
	
	private void viewAllFavourites() {
		MovieController viewAllFavouritesController = new MovieController();
		viewAllFavouritesController.viewFavourites();
    }
	
	private void viewAllMoviesOrSeriesByActor() {
		MovieController viewAllMoviesOrSeriesByActor = new MovieController();
		viewAllMoviesOrSeriesByActor.viewAllMoviesOrSeriesByActor();
    }
	
	private void viewAllMoviesOrSeriesByGenre() {
		MovieController viewAllMoviesOrSeriesByGenreController = new MovieController();
		viewAllMoviesOrSeriesByGenreController.viewAllMoviesOrSeriesByGenre();
    }
	
	private void backToMainMenu() {
		MainController mainMenu = new MainController();
		mainMenu.run();
    }
	
	private void viewAllActors() {
		MovieController actors = new MovieController();
		actors.viewAllActors();;
    }
	
	private void deletePersonalAccountById() {
		UserController user = new UserController();
		user.deletePersonalAccountById();
    }
}