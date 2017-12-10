package controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import asg.cliche.Command;
import asg.cliche.Param;
import models.Movie;
import models.Rating;
import models.User;

public class DefaultMenu {

	  private String name;
	  private MovieReccomenderAPI movieApi;

	  public DefaultMenu(MovieReccomenderAPI movieApi, User user) {
	    this.movieApi = movieApi;
	    this.setName(user.firstName);
	  }
	  
	  
	  
	//-----User Commands-----//
	  @Command(description = "Get all users sorted by their Names (alphabetical)")
		public void getAllUsers() {
			TreeSet<User> sortedUsers = new TreeSet<User>();
			sortedUsers.addAll(movieApi.getUsers());
			Iterator<User> iter = sortedUsers.iterator();
			while(iter.hasNext()) {
				User u = iter.next();
				System.out.println(u.firstName + " " + u.lastName);
			}
		}
		
		@Command(description = "search a user by name")
		public void getUserByName(String name) {
			ArrayList<User> users = new ArrayList<User>();
			users.addAll(movieApi.getUsers());
			for(int i = 0; i < users.size(); i++) {
				if(users.get(i).firstName.toLowerCase().contains(name.toLowerCase())) {
					System.out.println(users.get(i));
				}
			}
		}
	  
	  @Command(description = "Get a Users detail")
	  public void getUser(@Param(name = "user-id") Long userID) {
	    User user = movieApi.getUserByUserID(userID);
	    System.out.println(user);
	  }
	 
	  public String getName() {
	    return name;
	  }
	  public void setName(String name) {
	    this.name = name;
	  }
	  
	  
	  
	//-----Movie Commands----//
	  @Command(description="Get a Movie by its ID")
		public Movie getMovie(@Param(name="Movie Id") Long id){
			return movieApi.getMovie(id);
		}
	  
	  @Command(description="Get a List of all movies sorted by their title (alphabetical)")
		public void getMovies(){
			TreeSet<Movie> sortedMovies = new TreeSet<Movie>();
			sortedMovies.addAll(movieApi.getMovies());
			Iterator<Movie> iter = sortedMovies.iterator();
			while(iter.hasNext()) {
				Movie u = iter.next();
				System.out.println(u.title);
			}
	  }
		
	  
   //-----Rating commands----//
	  
	  @Command(description = "Add a Rating")
	  public void addRating(@Param(name = "user-id") Long userID,
	      @Param(name = "movie-id") Long movieID, @Param(name = "user-rating") int userRating) {
		  movieApi.createRating(userID, movieID, userRating);
	  }
		
	  @Command(description="Get User Ratings")
		public Map<Long, Rating> getUserRating(@Param(name="User ID")long id){
			return movieApi.getUserRating(id);
		}
		
	  @Command(description="Get Movies Ratings")
		public Map<Long,Rating> getMovieRating(@Param(name="Movie Id")long id)
		{
			return movieApi.getMovieRating(id);
		}
		
	  @Command(description="Return a Rating")
		public Rating getRating(@Param(name="Rating Id")long id){
			return movieApi.getRating(id);
		}
		
	  @Command(description="Get All Ratings")
		public void getRatings(){
			movieApi.getRatings();
		}
	}


