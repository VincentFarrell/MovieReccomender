package models;

import static org.junit.Assert.*;

import org.junit.Test;

public class RatingTest
{ 
  Rating test = new Rating (0L, 2L, 3);

  @Test
  public void testCreate()
  {
    assertEquals (Long.valueOf(0L),		test.userID);
    assertEquals (Long.valueOf(2L),		test.movieID);
    assertEquals (3,	test.userRating); 
  }

  @Test
  public void testToString()
  {
    assertEquals ("Rating{" + test.id + ", 0, 2, 3}", test.toString());
  }
}