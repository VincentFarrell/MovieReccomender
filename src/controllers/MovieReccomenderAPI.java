package controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import models.User;

public class MovieReccomenderAPI {
	private Map<String, User> users = new HashMap<>();

	public Collection<User> getUsers() {
		return users.values();
	}

	public void deleteUsers() {
		users.clear();
	}

	public User createUser(String firstName, String lastName, String gender,
			String occupation, int age) {
		User user = new User(firstName, lastName, gender, occupation, age);
		users.put(firstName, user);
		return user;
	}

	public User getUser(String firstName) {
		return users.get(firstName);
	}

	public void deleteUser(String firstName) {
		users.remove(firstName);
	}
}