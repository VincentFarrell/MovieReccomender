package models;

import static models.Fixtures.Movies;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class MovieTest
{ 
	Movie testMatrix = new Movie ("The Matrix", "1999", "http://www.imdb.com/title/tt0133093/");
	Movie testAlien = new Movie ("Alien", "1979", "http://www.imdb.com/title/tt0078748/");
	
	
  @Test
  public void testCreate()
  {
    assertEquals ("The Matrix", 							testMatrix.title);
    assertEquals ("1999",									testMatrix.year);
    assertEquals ("http://www.imdb.com/title/tt0133093/",	testMatrix.url);
    
    assertEquals ("Alien", 									testAlien.title);
    assertEquals ("1979",									testAlien.year);
    assertEquals ("http://www.imdb.com/title/tt0078748/",	testAlien.url);
  }

  @Test
  public void testIds()
  {
    assertNotEquals(Movies[0].id, Movies[1].id);
  }

  @Test
  public void testToString()
  {
    assertEquals ("Movie{" + Movies[0].id + ", The Matrix, 1999, http://www.imdb.com/title/tt0133093/}", Movies[0].toString());
    assertEquals ("Movie{" + Movies[2].id + ", Alien, 1979, http://www.imdb.com/title/tt0078748/}", Movies[2].toString());
  }
}