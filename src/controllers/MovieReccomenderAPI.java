package controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Optional;

import models.Activity;
import models.Location;
import models.User;

public class MovieReccomenderAPI {

	private Map<Long, User> userIndex = new HashMap<>();
	private Map<String, User> nameIndex = new HashMap<>();
	private Map<Long, Activity> activitiesIndex = new HashMap<>();

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

	public void createActivity(Long id, String type, String location, double distance) {
		Activity activity = new Activity(type, location, distance);
		Optional<User> user = Optional.fromNullable(userIndex.get(id));
		if (user.isPresent()) {
			user.get().activities.put(activity.id, activity);
			activitiesIndex.put(activity.id, activity);
		}
	}

	public Activity getActivity(Long id) {
		return activitiesIndex.get(id);
	}

	public void addLocation(Long id, float latitude, float longitude) {
		Optional<Activity> activity = Optional.fromNullable(activitiesIndex.get(id));
		if (activity.isPresent()) {
			activity.get().route.add(new Location(latitude, longitude));
		}
	}
}
