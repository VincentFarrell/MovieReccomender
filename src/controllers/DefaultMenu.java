package controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import asg.cliche.Command;
import asg.cliche.Param;
import models.User;

public class DefaultMenu {

	  private String name;
	  private MovieReccomenderAPI movieApi;

	  public DefaultMenu(MovieReccomenderAPI movieApi, User user) {
	    this.movieApi = movieApi;
	    this.setName(user.firstName);
	  }
	  
	  @Command(description = "Get a Users detail")
	  public void getUser(@Param(name = "user-id") Long id) {
	    User user = movieApi.getUserByUserID(id);
	    System.out.println(user);
	  }
	  
	  @Command(description = "Add a Rating")
	  public void addActivity(@Param(name = "user-id") Long userID,
	      @Param(name = "movie-id") Long movieID, @Param(name = "user-rating") int userRating) {
		  movieApi.createRating(userID, movieID, userRating);
	  }
	  

	  public String getName() {
	    return name;
	  }
	  public void setName(String name) {
	    this.name = name;
	  }
	  
	  @Command(description = "Get all users sorted by there Name")
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
	}