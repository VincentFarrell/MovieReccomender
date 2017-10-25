package controllers;

import java.util.ArrayList;
import java.util.List;

import models.User;

public class MovieReccomenderAPI
{
  private List <User> users = new ArrayList<User>();

  public List<User> getUsers ()
  {
    return users;
  }

  public  void deleteUsers() 
  {
    users.clear();
  }

  public User createUser(String firstName, String lastName, String gender, String occupation, int age) 
  {
    User user = new User (firstName, lastName, gender, occupation, age);
    users.add(user);
    return user;
  }

  public User getUser(String firstName) 
  {
    for (User user : users)
    {
      if (firstName.equals(user.firstName))
        return user;
    }
    return null;
  }

  public void deleteUser(String firstName) 
  {
    User foundUser = null;
    for (User user : users)
    {
      if (firstName.equals(user.firstName))
        foundUser = user;
    }
    if (foundUser != null)
    {
      users.remove(foundUser);
    }
  }
}