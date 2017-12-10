package controllers;

import static models.Fixtures.users;
import static models.Fixtures.ratings;
import static models.Fixtures.movies;
import static org.junit.Assert.*;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllers.MovieReccomenderAPI;
import models.User;
import models.Rating;
import models.Movie;

	public class MovieReccomenderAPITest {
	  private MovieReccomenderAPI movieReccomender;

	  @Before
	  public void setup()
	  {
	    movieReccomender = new MovieReccomenderAPI();
	    for (User user : users)
	    {
	    	movieReccomender.createUser(user.firstName, user.lastName, user.gender, user.occupation, user.age);
	    }
	    for (Movie movie : movies) {
			movieReccomender.addMovies( movie.title, movie.year, movie.url);
		}
	  }

	  @After
	  public void tearDown()
	  {
		  movieReccomender = null;
	  }
	  
	  @Test
	  public void testAddMovie() {
			assertEquals(movies.length, movieReccomender.getMovies().size());
			movieReccomender.addMovies("The Matrix", "1999", "http://www.imdb.com/title/tt0133093/");
			assertEquals(movies.length + 1, movieReccomender.getMovies().size());
			assertEquals(movies[0], movieReccomender.getMovieByTitle(movies[0].title));
			
	}

	  @Test
	  public void testUser()
	  {
	    assertEquals (users.length, movieReccomender.getUsers().size());
	    movieReccomender.createUser("homer", "simpson", "male", "unemployed", 40);
		    assertEquals (users[0], movieReccomender.getUserByUserID(users[0].id));
    assertEquals (users.length+1, movieReccomender.getUsers().size());
	  }  

	  @Test
	  public void testUsers()
	  {
	    assertEquals (users.length, movieReccomender.getUsers().size());
	    for (User user: users)
	    {
	      User eachUser = movieReccomender.getUserByUserID(user.id);
	      assertEquals (user, eachUser);
	      assertNotSame(user, eachUser);
	    }
	  }

	  @Test
	  public void testDeleteUsers()
	  {
	    assertEquals (users.length, movieReccomender.getUsers().size());
	    User marge = movieReccomender.getUser(users[0].id);
	    movieReccomender.deleteUser(marge.id);
	    assertEquals (users.length-1, movieReccomender.getUsers().size());    
	  }  
	  
		@Test
		public void testUserLogin() {
			//Checking with admin lisa login
			assertTrue(movieReccomender.login(users[0].id, users[0].lastName));
			assertEquals(movieReccomender.currentUser.get(), users[0]);
			
			//check logout
			movieReccomender.logout();
			assertEquals(movieReccomender.currentUser, Optional.absent());
			
			//check login fail
			assertFalse(movieReccomender.login(users[0].id, "paddy"));
			assertEquals(movieReccomender.currentUser, Optional.absent());
			
		}
	}