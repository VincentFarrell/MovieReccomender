package controllers;

import java.io.File;
import java.util.Collection;

import models.User;
import utils.Serializer;
import utils.XMLSerializer;

public class Main {
	public static void main(String[] args) throws Exception {

		File datastore = new File("datastore3.xml");
		Serializer serializer = new XMLSerializer(datastore);

		MovieReccomenderAPI movieReccomenderAPI = new MovieReccomenderAPI(serializer);
		if (datastore.isFile()) {
			movieReccomenderAPI.load();
		}

		movieReccomenderAPI.createUser("Bart", "Simpson", "Male", "Unemployed", 10);
		movieReccomenderAPI.createUser("Homer", "Simpson", "Male", "Unemployed", 40);
		movieReccomenderAPI.createUser("Lisa", "Simpson", "Female", "Unemployed", 11);

		Collection<User> users = movieReccomenderAPI.getUsers();
		System.out.println(users);

		   User homer = movieReccomenderAPI.getUserByName("Homer");
		   movieReccomenderAPI.createRating(homer.id, 0L, 1L, 8.5);

		movieReccomenderAPI.deleteUser(homer.id);
		users = movieReccomenderAPI.getUsers();
		System.out.println(users);

		movieReccomenderAPI.store();
	}
}
