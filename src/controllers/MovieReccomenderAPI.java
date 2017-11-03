package controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Optional;

import models.Rating;
import models.Movie;
import models.User;

public class MovieReccomenderAPI {

	private Map<Long, User> userIndex = new HashMap<>();
	private Map<String, User> nameIndex = new HashMap<>();
	private Map<Long, Rating> ratingsIndex = new HashMap<>();

	public Collection<User> getUsers() {
		return userIndex.values();
	}

	public void deleteUsers() {
		userIndex.clear();
		nameIndex.clear();
	}

	public User createUser(String firstName, String lastName, String gender, String occupation, int age) {
		User user = new User(firstName, lastName, gender, occupation, age);
		userIndex.put(user.id, user);
		nameIndex.put(firstName, user);
		return user;
	}

	public User getUserByName(String firstName) {
		return nameIndex.get(firstName);
	}

	public User getUser(Long id) {
		return userIndex.get(id);
	}

	public void deleteUser(Long id) {
		User user = userIndex.remove(id);
		nameIndex.remove(user.firstName);
	}

	
	
	public Rating createRating(Long id, Long userID, Long movieID, Double userRating) {
		Rating rating = null;
		Optional<User> user = Optional.fromNullable(userIndex.get(id));
		if (user.isPresent()) {
			rating = new Rating(userID, movieID, userRating);
			user.get().ratings.put(rating.id, rating);
			ratingsIndex.put(rating.id, rating);
		}
		return rating;
	}

	public Rating getRating(Long id) {
		return ratingsIndex.get(id);
	}

	
	
	public void addMovie(Long id, String title, String year, String url) {
		Optional<Rating> rating = Optional.fromNullable(ratingsIndex.get(id));
		if (rating.isPresent()) {
			rating.get().rating.add(new Movie(title, year, url));
		}
	}
}
