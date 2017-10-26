package models;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
	private User[] users = { new User("marge", "simpson", "female", "unemployed", 5),
			new User("lisa", "simpson", "female", "unemployed", 10),
			new User("bart", "simpson", "male", "unemployed", 11),
			new User("maggie", "simpson", "female", "unemployed", 3) };
	User homer = new User("homer", "simpson", "male", "unemployed", 40);

	@Test
	public void testCreate() {
		assertEquals("homer", homer.firstName);
		assertEquals("simpson", homer.lastName);
		assertEquals("male", homer.gender);
		assertEquals("unemployed", homer.occupation);
		assertEquals(40, homer.age);
	}

	@Test
	public void testIds() {
		Set<Long> ids = new HashSet<>();
		for (User user : users) {
			ids.add(user.id);
		}
		assertEquals(users.length, ids.size());
	}

	@Test
	public void testToString() {
		assertEquals("User{" + homer.id + ", homer, simpson, male, unemployed, 40}", homer.toString());
	}
}