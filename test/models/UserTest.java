package models;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

import static models.Fixtures.users;

public class UserTest
{
  User homer = new User ("homer", "simpson", "m",  "unemployed", 40);

  @Test
  public void testCreate()
  {
    assertEquals ("homer",               homer.firstName);
    assertEquals ("simpson",             homer.lastName);
    assertEquals ("m",   			 	 homer.gender);   
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
    assertEquals ("User{" + homer.id + ", homer, simpson, m, unemployed, 40}", homer.toString());
  }
  
  @Test
  public void testEquals()
  {
    User homer2 = new User ("homer", "simpson", "m",  "unemployed", 40); 
    User bart   = new User ("bart", "simpson", "m",  "unemployed", 10); 

    assertEquals(homer, homer);
    assertEquals(homer, homer2);
    assertNotEquals(homer, bart);
    assertSame(homer, homer);
    assertNotSame(homer, homer2);
  }
}