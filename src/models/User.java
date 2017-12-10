package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.HashMap;
import java.util.Map;
import models.Rating;
import utils.ToJsonString;
import controllers.MovieReccomenderAPI;

import com.google.common.base.Objects;

public class User implements Comparable<User> {

	public Map<Long, Rating> ratings = new HashMap<>();

	// initialize variables for User
	static Long counter = 0l;
	public Long id;
	public String firstName;
	public String lastName;
	public String gender;
	public String occupation;
	public int age;
	public String role;

	public User(String firstName, String lastName, String gender, String occupation, int age) {
		this(firstName, lastName, gender, occupation, age, "default");
	}

	// constructors for User
	public User(String firstName, String lastName, String gender, String occupation, int age, String role) {
		this.id = counter++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.occupation = occupation;
		this.age = age;
		this.role = role;
	}

	

	  public String toString()
	  {
	    return toStringHelper(this).addValue(id)
	                               .addValue(firstName)
	                               .addValue(lastName)
	                               .addValue(gender)
	                               .addValue(occupation) 
	                               .addValue(age) 
	                               .toString();
	  }

	@Override
	public int hashCode() {
		return Objects.hashCode(this.lastName, this.firstName, this.gender, this.occupation, this.age);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof User) {
			final User other = (User) obj;
			return Objects.equal(firstName, other.firstName) && Objects.equal(lastName, other.lastName)
					&& Objects.equal(gender, other.gender) && Objects.equal(occupation, other.occupation)
					&& Objects.equal(age, other.age);
		} else {
			return false;
		}
	}

	
	//return (CompareTo method for implement)
	@Override
	public int compareTo(User user) {
		return this.firstName.compareTo(user.firstName);
	}
}