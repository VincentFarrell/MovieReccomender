package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;

import asg.cliche.Command;
import asg.cliche.Param;
import models.Movie;
import models.Rating;
import models.User;

public class AdminMenu {

	private String name;
	private MovieReccomenderAPI movieApi;

	public AdminMenu(MovieReccomenderAPI movieApi, String userName) {

		this.movieApi = movieApi;
		this.setName(userName);
	}

	
	
	
 //-----User Commands----//
	// return all users
	@Command(description = "Get all users details")
	public void getUsers() {

		Collection<User> users = movieApi.getUsers();
		System.out.println(users);

	}

	// get name
	public String getName() {
		return name;
	}

	// set name
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
	public void getUser(@Param(name = "user-id") Long userID) {

		User user = movieApi.getUserByUserID(userID);
		System.out.println(user);

	}

	@Command(description = "Delete a User")
	public void deleteUser(@Param(name = "user-id") Long id) {

		movieApi.deleteUser(id);
	}

	@Command(description = "Get all users sorted by there Name")
	public void getAllUsers() {
		TreeSet<User> sortedUsers = new TreeSet<User>();
		sortedUsers.addAll(movieApi.getUsers());
		Iterator<User> iter = sortedUsers.iterator();
		while (iter.hasNext()) {
			User u = iter.next();
			System.out.println(u.firstName + " " + u.lastName);
		}
	}

	@Command(description = "search a user by name")
	public void getUserByName(String name) {
		ArrayList<User> users = new ArrayList<User>();
		users.addAll(movieApi.getUsers());
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).firstName.toLowerCase().contains(name.toLowerCase())) {
				System.out.println(users.get(i));
			}
		}
	}

	
	// -----Movie Commands-----//

	@Command(description = "Add a Movie")
	public void addMovies(@Param(name = "id") Long id, @Param(name = "title") String title,
			@Param(name = "year") String year, @Param(name = "url") String url) {

		movieApi.addMovies(title, year, url);
	}
	
	@Command(description="Get a Movie by its ID")
	public Movie getMovie(@Param(name="Movie Id") Long id){
		return movieApi.getMovie(id);
	}
	
	@Command(description="Get movie by title")
	public void getMovieByTitle(@Param(name="title")String title) {
		ArrayList<Movie> movies = new ArrayList<Movie>();
		movies.addAll(movieApi.getMovies());
		for(int i = 0; i < movies.size(); i++) {
			if(movies.get(i).title.toLowerCase().contains(title.toLowerCase())) {
				System.out.println(movies.get(i));
			}
		}
		
	}

	
	
	// ----Rating commands----//

	@Command(description = "Add a Rating to Movie")
	public void createRating(@Param(name = "user-id") Long userID, @Param(name = "movie-id") Long movieID,
			@Param(name = "user-rating") int userRating) {
		movieApi.createRating(userID, movieID, userRating);
	}

	@Command(description = "Get User Ratings")
	public Map<Long, Rating> getUserRating(@Param(name = "User ID") long id) {
		return movieApi.getUserRating(id);
	}

	@Command(description = "Get Movies Ratings")
	public Map<Long, Rating> getMovieRating(@Param(name = "Movie Id") long id) {
		return movieApi.getMovieRating(id);
	}

	@Command(description = "Return a Rating")
	public Rating getRating(@Param(name = "Rating Id") long id) {
		return movieApi.getRating(id);
	}

	@Command(description = "Get All Ratings")
	public void getRatings() {
		movieApi.getRatings();
	}

	@Command(description = "Delete a Rating")
	public void deleteRating(@Param(name = "Rating Id") long id) {
		User user = movieApi.currentUser.get();
		if (user.id != id) {
			System.out.println("You can not delete another person rating");
		} else {
			movieApi.deleteRating(id);
		}
	}

}
