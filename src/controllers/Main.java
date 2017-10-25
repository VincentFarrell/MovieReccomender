package controllers;

import java.io.IOException;
import java.util.Collection;

import models.User;

public class Main {
	public static void main(String[] args) throws IOException {
		MovieReccomenderAPI moviereccomenderAPI = new MovieReccomenderAPI();

		moviereccomenderAPI.createUser("Bart", "Simpson", "Male", "Unemployed",
				10);
		moviereccomenderAPI.createUser("Homer", "Simpson", "Male",
				"Unemployed", 40);
		moviereccomenderAPI.createUser("Lisa", "Simpson", "Female",
				"Unemployed", 11);

		Collection<User> users = moviereccomenderAPI.getUsers();
		System.out.println(users);
	}
}
