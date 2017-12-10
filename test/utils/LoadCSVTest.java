package utils;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import models.Movie;
import models.Rating;
import models.User;

public class LoadCSVTest {
	
	//custom .dat files were made for short tests containing 5-10 entries
	private LoadCSV loader;
	private String usersData;
	private String moviesData;
	private String ratingsData;
	
	//5 for Users
	private List<User> users;
	private User user1;
	private User user2;
	private User user3;
	private User user4;
	private User user5;
	
	//10 for Movies
	private List<Movie> movies;
	private Movie movie1;
	private Movie movie2;
	private Movie movie3;
	private Movie movie4;
	private Movie movie5;
	private Movie movie6;
	private Movie movie7;
	private Movie movie8;
	private Movie movie9;
	private Movie movie10;

	//and 5 for Ratings
	private List<Rating> ratings;
	private Rating rating1;
	private Rating rating2;
	private Rating rating3;
	private Rating rating4;
	private Rating rating5;

	@Before
	public void setUp() {
		loader = new LoadCSV();
		usersData = "Data/usersTest.dat";
		moviesData = "Data/itemsTest.dat";
		ratingsData = "Data/ratingsTest.dat";
		
		// Setting up a List of type user that is identical to what should output
		// when the LoadCSVTest reads in usersTest.
		user1 = new User("Leonard", "Hernandez", "M", "technician", 24);
		user2 = new User("Melody", "Roberson", "F", "other", 53);
		user3 = new User("Gregory", "Newton", "M", "writer", 23);
		user4 = new User("Oliver", "George", "M", "technician", 24);
		user5 = new User("Jenna", "Parker", "F", "other", 33);
		users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		users.add(user5);
		
		//Doing the same With moviesTest
		movie1 = new Movie(1l, "Toy Story (1995)", "01-Jan-1995", "http://us.imdb.com/M/title-exact?Toy%20Story%20(1995)");
		movie2 = new Movie(2l, "GoldenEye (1995)", "01-Jan-1995", "http://us.imdb.com/M/title-exact?GoldenEye%20(1995)");
		movie3 = new Movie(3l, "Four Rooms (1995)", "01-Jan-1995", "http://us.imdb.com/M/title-exact?Four%20Rooms%20(1995)");
		movie4 = new Movie(4l, "Get Shorty (1995)", "01-Jan-1995", "http://us.imdb.com/M/title-exact?Get%20Shorty%20(1995)");
		movie5 = new Movie(5l, "Copycat (1995)", "01-Jan-1995", "http://us.imdb.com/M/title-exact?Copycat%20(1995)");
		movie6 = new Movie(6l, "Shanghai Triad (Yao a yao yao dao waipo qiao) (1995)", "01-Jan-1995", "http://us.imdb.com/Title?Yao+a+yao+yao+dao+waipo+qiao+(1995)");
		movie7 = new Movie(7l, "Twelve Monkeys (1995)", "01-Jan-1995", "http://us.imdb.com/M/title-exact?Twelve%20Monkeys%20(1995)");
		movie8 = new Movie(8l, "Babe (1995)", "01-Jan-1995", "http://us.imdb.com/M/title-exact?Babe%20(1995)");
		movie9 = new Movie(9l, "Dead Man Walking (1995)", "01-Jan-1995", "http://us.imdb.com/M/title-exact?Dead%20Man%20Walking%20(1995)");
		movie10 = new Movie(10l, "Richard III (1995)", "22-Jan-1996", "http://us.imdb.com/M/title-exact?Richard%20III%20(1995)");
		movies = new ArrayList<Movie>();
		movies.add(movie1);
		movies.add(movie2);
		movies.add(movie3);
		movies.add(movie4);
		movies.add(movie5);
		movies.add(movie6);
		movies.add(movie7);
		movies.add(movie8);
		movies.add(movie9);
		movies.add(movie10);
		
		// And again for ratingsTest
		rating1 = new Rating(0l, 196l, 242l, 1);
		rating2 = new Rating(1l, 186l, 302l, 1);
		rating3 = new Rating(2l, 22l, 377l, -5);
		rating4 = new Rating(3l, 244l, 51l, -3);
		rating5 = new Rating(4l, 166l, 346l, -5);
		ratings = new ArrayList<Rating>();
		ratings.add(rating1);
		ratings.add(rating2);
		ratings.add(rating3);
		ratings.add(rating4);
		ratings.add(rating5);

	}

	//toStrings for testLoad
	
	@Test
	public void testLoadUsers() throws Exception {
		assertEquals(loader.loadUsers(usersData).toString(), users.toString());
	}

	@Test
	public void testLoadMovies() throws Exception {
		assertEquals(loader.loadMovies(moviesData).toString(), movies.toString());
	}

	@Test
	public void testLoadRatings() throws Exception {
		assertEquals(loader.loadRatings(ratingsData).toString(), ratings.toString());
	}

}