package models;

import static com.google.common.base.MoreObjects.toStringHelper;
import com.google.common.base.Objects;

public class User {
	public String firstName;
	public String lastName;
	public String gender;
	public String occupation;
	public int age;

	public User() {
	}

	public User(String firstName, String lastName, String gender,
			String occupation, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.occupation = occupation;
		this.age = age;
	}

	@Override
	public String toString() {
		return toStringHelper(this).addValue(firstName).addValue(lastName)
				.addValue(gender).addValue(occupation).addValue(age).toString();
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.lastName, this.firstName, this.gender,
				this.occupation, this.age);
	}
}