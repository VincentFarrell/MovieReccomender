package controllers;

import java.util.Collection;
import java.util.Optional;

import asg.cliche.Command;
import asg.cliche.Param;
import models.Movie;
import models.User;

public class AdminMenu {

	private String name;
	private MovieReccomenderAPI movieApi;

	public AdminMenu(MovieReccomenderAPI movieApi, String userName) {

		this.movieApi = movieApi;
		this.setName(userName);
	}

	
	//return all users
	@Command(description = "Get all users details")
	public void getUsers() {

		Collection<User> users = movieApi.getUsers();
		System.out.println(users);

	}

	//get name
	public String getName() {
		return name;
	}
	//set name
	public void setName(String name) {
		this.name = name;
	}

	@Command(description = "Create a new User")
	public void createUser(@Param(name = "first name") String firstName, @Param(name = "last name") String lastName,
			@Param(name = "gender") String gender, @Param(name = "occupation") String occupation,
			@Param(name = "age") int age) {

		movieApi.createUser(firstName, lastName, gender, occupation, age);

	}

	@Command(description = "Get a Users detail")
	public void getUser(@Param(name = "user-id") Long id) {

		User user = movieApi.getUserByUserID(id);
		System.out.println(user);

	}

	@Command(description = "Delete a User")
	public void deleteUser(@Param(name = "user-id") Long id) {

			movieApi.deleteUser(id);
	}
	
	@Command(description = "Add a Rating to Movie")
	public void createRating( @Param(name = "user-id") Long userID,
	      @Param(name = "movie-id") Long movieID, @Param(name = "user-rating") int userRating) {
		  Optional<Movie> movie = Optional.ofNullable(movieApi.getMovie(movieID));
		  if (movie.isPresent()) {
			  movieApi.createRating(userID, movieID, userRating);
		  }	  
	}

	@Command(description = "Add a Movie")
	public void addMovies(@Param(name = "id") Long id, @Param(name = "title") String title,
	      @Param(name = "year") String year, @Param(name = "url") String url) {
	  
	      movieApi.addMovies(title, year, url);
	}
}