package models;

import static org.junit.Assert.*;

import org.junit.Test;

public class RatingTest
{ 
  Rating test = new Rating (0L, 2L, 9.5);

  @Test
  public void testCreate()
  {
    assertEquals (Long.valueOf(0L),		test.userID);
    assertEquals (Long.valueOf(2L),		test.movieID);
    assertEquals (Double.valueOf(9.5),	test.userRating); 
  }

  @Test
  public void testToString()
  {
    assertEquals ("Rating{" + test.id + ", 0, 2, 9.5}", test.toString());
  }
}