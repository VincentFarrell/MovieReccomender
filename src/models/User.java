package models;

import static com.google.common.base.MoreObjects.toStringHelper;

public class User 
{
  public String firstName;
  public String lastName;
  public String gender;
  public String occupation;
  public int age;
  
  public User()
  {
  }
  
  public User(String firstName, String lastName, String gender, String occupation, int age)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
    this.occupation = occupation;
    this.age = age;
  }
  
  public String toString()
  {
    return toStringHelper(this).addValue(firstName)
                               .addValue(lastName)
                               .addValue(gender)
                               .addValue(occupation)
                               .addValue(age)
                               .toString();
  }
}