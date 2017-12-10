package controllers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.common.base.Optional;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import models.Movie;
import models.Rating;
import models.User;
import utils.Serializer;

public class MovieReccomenderAPI {

	private Serializer serializer;

	Optional<User> currentUser;

	public MovieReccomenderAPI() {
	}

	public MovieReccomenderAPI(Serializer serializer) {
		this.serializer = serializer;
	}

	// -----Login/Logout Methods-----//

	// simplified login method
	public boolean login(Long userID, String password) {
		Optional<User> user = Optional.fromNullable(userIndex.get(userID));
		if (user.isPresent() && user.get().firstName.equals(password)) {
			currentUser = user;
			return true;
		}
		return false;
	}

	// simplified logout method
	public void logout() {
		Optional<User> user = currentUser;
		if (user.isPresent()) {
			currentUser = Optional.absent();
		}
	}

	@SuppressWarnings("unchecked")
	public void load() throws Exception {
		serializer.read();
		ratingsIndex = (Map<Long, Rating>) serializer.pop();
		nameIndex = (Map<String, User>) serializer.pop();
		userIndex = (Map<Long, User>) serializer.pop();
		movieIndex = (Map<Long, Movie>) serializer.pop();
	}

	void store() throws Exception {
		serializer.push(userIndex);
		serializer.push(nameIndex);
		serializer.push(ratingsIndex);
		serializer.push(movieIndex);
		serializer.write();
	}

	private Map<Long, User> userIndex = new HashMap<>();
	private Map<String, User> nameIndex = new HashMap<>();
	private Map<Long, Rating> ratingsIndex = new HashMap<>();
	private Map<Long, Movie> movieIndex = new HashMap<>();
	private Map<String, Movie> movieTitle = new HashMap<>();

	// ------User Methods-----//

	// return all Users
	public Collection<User> getUsers() {
		return userIndex.values();
	}

	// delete all Users
	public void deleteUsers() {
		userIndex.clear();
		nameIndex.clear();
	}

	// create user with @Param firstName, lastName, gender, occ, age
	public void createUser(String firstName, String lastName, String gender, String occupation, int age) {
		User user = new User(firstName, lastName, gender, occupation, age);
		userIndex.put(user.id, user);
	}

	public void createAdminUser(String firstName, String lastName, String gender, String occupation, int age, String role) {
		User user = new User(firstName, lastName, gender, occupation, age, role);
		userIndex.put(user.id, user);

	}

	// return User by UserID (return by name in Admin/Default Menu
	@SuppressWarnings("unlikely-arg-type")
	public User getUserByUserID(Long userID) {
		return nameIndex.get(userID);
	}

	// return user by id
	public User getUser(Long id) {
		return userIndex.get(id);
	}

	// delete user by ID
	public void deleteUser(Long id) {
		User user = userIndex.remove(id);
		nameIndex.remove(user.firstName);
	}

	// -----Rating methods-----//

	// create a new rating
	public void createRating(Long userID, Long movieID, int userRating) {
		Rating rating;
		Optional<User> user = Optional.fromNullable(userIndex.get(userID));
		Optional<Movie> movie = Optional.fromNullable(movieIndex.get(movieID));
		if (movie.isPresent() && user.isPresent()) {
			rating = new Rating(userID, movieID, userRating); // create a new rating
			user.get().ratings.put(rating.id, rating); // add rating to user
			movie.get().ratings.put(rating.id, rating); // add rating to movie
			ratingsIndex.put(rating.id, rating); // add rating to data
		}
	}

	// return rating by ID
	public Rating getRating(Long id) {
		return ratingsIndex.get(id);
	}

	//get all ratings
	public Collection<Rating> getRatings() {
		return ratingsIndex.values();
	}

	//get user ratings
	
	public Map<Long, Rating> getUserRating(long id) {
		Optional<User> user = Optional.fromNullable(userIndex.get(id));
		return user.get().ratings;
	}

	//get movie ratings
	public Map<Long, Rating> getMovieRating(long id) {
		Optional<Movie> movie = Optional.fromNullable(movieIndex.get(id));
		return movie.get().ratings;

	}

	public void deleteRating(long id) {
		ratingsIndex.remove(id);
	}

	// ------Movie methods-----//

	// add a movie to data
	public void addMovies(String title, String year, String url) {
		Movie movie = new Movie(title, year, url);
		movieIndex.put(movie.id, movie);
	}

	// return a list of all movies in data
	public Collection<Movie> getMovies() {
		return movieIndex.values();
	}

	// return movie by ID
	public Movie getMovie(Long id) {
		return movieIndex.get(id);
	}

	// return movie by title
	public Movie getMovieByTitle(String title) {
		return movieTitle.get(title);
	}

	@SuppressWarnings("unchecked")
	void load(File file) throws Exception {
		ObjectInputStream is = null;
		try {
			XStream xstream = new XStream(new DomDriver());
			is = xstream.createObjectInputStream(new FileReader(file));
			userIndex = (Map<Long, User>) is.readObject();
			nameIndex = (Map<String, User>) is.readObject();
			ratingsIndex = (Map<Long, Rating>) is.readObject();
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

	void store(File file) throws Exception {
		XStream xstream = new XStream(new DomDriver());
		ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(file));
		out.writeObject(userIndex);
		out.writeObject(nameIndex);
		out.writeObject(ratingsIndex);
		out.close();
	}

	public void initalLoad() throws IOException {
		String delims = "[|]";
		Scanner scanner = new Scanner(new File("./Data/usersTest.dat"));
		while (scanner.hasNextLine()) {
			String userDetails = scanner.nextLine();
			// parse user details string
			String[] userTokens = userDetails.split(delims);

			if (userTokens.length == 7) {
				createUser(userTokens[1], userTokens[2], userTokens[5], userTokens[4], Integer.valueOf(userTokens[3]));
			} else {
				scanner.close();
				throw new IOException("Invalid member length: " + userTokens.length);
			}
		}

		scanner = new Scanner(new File("./Data/itemsTest.dat"));
		while (scanner.hasNextLine()) {
			String movieDetails = scanner.nextLine();
			// parse user details string
			String[] movieTokens = movieDetails.split(delims);

			if (movieTokens.length == 23) {
				addMovies(movieTokens[1], movieTokens[2], movieTokens[3]);
			} else {
				scanner.close();
				throw new IOException("Invalid member length: " + movieTokens.length);
			}
		}

		scanner = new Scanner(new File("./Data/ratingsTest.dat"));
		while (scanner.hasNextLine()) {
			String userDetails2 = scanner.nextLine();
			// parse user details string
			String[] userTokens2 = userDetails2.split(delims);
			if (userTokens2.length == 4) {
				createRating(Long.valueOf(userTokens2[0]), Long.valueOf(userTokens2[1]),
						Integer.valueOf(userTokens2[2]));
			} else {
				scanner.close();
				throw new IOException("Invalid member length: " + userTokens2.length);
			}

		}
		scanner.close();

	}
}
