package models;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

import static models.Fixtures.users;

public class UserTest
{
  User homer = new User ("homer", "simpson", "male",  "unemployed", 40);

  @Test
  public void testCreate()
  {
    assertEquals ("homer",               homer.firstName);
    assertEquals ("simpson",             homer.lastName);
    assertEquals ("male",   			 homer.gender);   
    assertEquals ("unemployed",          homer.occupation);  
    assertEquals (40,              		 homer.age); 
  }

  @Test
  public void testIds()
  {
    Set<Long> ids = new HashSet<>();
    for (User user : users)
    {
      ids.add(user.id);
    }
    assertEquals (users.length, ids.size());
  }

  @Test
  public void testToString()
  {
    assertEquals ("User{" + homer.id + ", homer, simpson, male, unemployed, 40}", homer.toString());
  }
}