package controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import models.User;

public class MovieReccomenderAPI {

	private Map<Long, User> userIndex = new HashMap<>();
	private Map<String, User> nameIndex = new HashMap<>();

	public Collection<User> getUsers() {
		return userIndex.values();
	}

	public void deleteUsers() {
		userIndex.clear();
		nameIndex.clear();
	}

	public User createUser(String firstName, String lastName, String gender,
			String occupation, int age) {
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
}