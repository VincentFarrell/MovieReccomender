package models;

import static models.Fixtures.users;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllers.MovieReccomenderAPI;

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
	  }

	  @After
	  public void tearDown()
	  {
		  movieReccomender = null;
	  }

	  @Test
	  public void testUser()
	  {
	    assertEquals (users.length, movieReccomender.getUsers().size());
	    movieReccomender.createUser("homer", "simpson", "male", "unemployed", 40);
	    assertEquals (users.length+1, movieReccomender.getUsers().size());
	    assertEquals (users[0], movieReccomender.getUserByName(users[0].firstName));
	  }  

	  @Test
	  public void testUsers()
	  {
	    assertEquals (users.length, movieReccomender.getUsers().size());
	    for (User user: users)
	    {
	      User eachUser = movieReccomender.getUserByName(user.firstName);
	      assertEquals (user, eachUser);
	      assertNotSame(user, eachUser);
	    }
	  }

	  @Test
	  public void testDeleteUsers()
	  {
	    assertEquals (users.length, movieReccomender.getUsers().size());
	    User marge = movieReccomender.getUserByName("marge");
	    movieReccomender.deleteUser(marge.id);
	    assertEquals (users.length-1, movieReccomender.getUsers().size());    
	  }  
	}