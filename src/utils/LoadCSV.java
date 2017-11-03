package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import models.Movie;
import models.Rating;
import models.User;

public class LoadCSV {

	public List<User> loadUsers(String Filename) throws IOException {

		String delims = "[|]";
		Scanner scanner = new Scanner(Filename);
		List<User> users = new ArrayList<User>();

		while (scanner.hasNextLine()) {
			String userDetails = scanner.nextLine();
			// parse user details string
			String[] userTokens = userDetails.split(delims);

			if (userTokens.length == 7) {
				Long id = Long.parseLong(userTokens[0]);
				String firstName = userTokens[1];
				String lastName = userTokens[2];
				int age = Integer.parseInt(userTokens[3]);
				String gender = userTokens[4];
				String occupation = userTokens[5];

				users.add(new User(id, firstName, lastName, gender, occupation, age));

			} else {
				scanner.close();
				throw new IOException("Invalid member length: " + userTokens.length);
			}
		}
		scanner.close();
		return users;
	}

	@SuppressWarnings("resource")
	public List<Movie> loadMovies(String filename) throws Exception {
		String delims = "[|]";
		Scanner scanner = new Scanner(filename);

		List<Movie> movies = new ArrayList<Movie>();
		while (scanner.hasNextLine()) {
			String movieDetails = scanner.nextLine();
			String[] movieTokens = movieDetails.split(delims);

			if (movieTokens.length == 4) {
				Long id = Long.parseLong(movieTokens[0]);
				String title = movieTokens[1];
				String year = movieTokens[2];
				String url = movieTokens[3];

				movies.add(new Movie(id, title, year, url));
			}

			else {
				throw new Exception("Invalid member length: " + movieTokens.length);
			}
		}
		scanner.close();
		return movies;
	}

	@SuppressWarnings("resource")
	public List<Rating> loadRatings(String filename) throws Exception {
		Scanner scanner = new Scanner(filename);
		String delims = "[|]";
		List<Rating> ratings = new ArrayList<Rating>();
		while (scanner.hasNextLine()) {
			String ratingDetails = scanner.nextLine();
			String[] ratingTokens = ratingDetails.split(delims);
			if (ratingTokens.length == 3) {
				Long userID = Long.parseLong(ratingTokens[0]);
				Long movieID = Long.parseLong(ratingTokens[1]);
				int rating = Integer.parseInt(ratingTokens[2]);

				ratings.add(new Rating(userID, movieID, rating));
			}

			else {
				throw new Exception("Invalid member length: " + ratingTokens.length);
			}
		}
		scanner.close();
		return ratings;
	}

}
