package controllers;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Optional;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import models.Movie;
import models.Rating;
import models.User;
import utils.Serializer;

public class MovieReccomenderAPI {

	private Serializer serializer;
	
	public MovieReccomenderAPI() 
	{
	}

	public MovieReccomenderAPI(Serializer serializer) {
		this.serializer = serializer;
	}
	
	  @SuppressWarnings("unchecked")
	  public void load() throws Exception
	  {
	    serializer.read();
	    ratingsIndex = (Map<Long, Rating>) serializer.pop();
	    nameIndex      = (Map<String, User>)   serializer.pop();
	    userIndex       = (Map<Long, User>)     serializer.pop();
	  }

	  void store() throws Exception
	  {
	    serializer.push(userIndex);
	    serializer.push(nameIndex);
	    serializer.push(ratingsIndex);
	    serializer.write(); 
	  }

	  
	  
	
	private Map<Long, User> userIndex = new HashMap<>();
	private Map<String, User> nameIndex = new HashMap<>();
	private Map<Long, Rating> ratingsIndex = new HashMap<>();

	
	public Collection<User> getUsers() {
		return userIndex.values();
	}

	public void deleteUsers() {
		userIndex.clear();
		nameIndex.clear();
	}

	public User createUser(String firstName, String lastName, String gender, String occupation, int age) {
		User user = new User(firstName, lastName, gender, occupation, age);
		userIndex.put(user.id, user);
		nameIndex.put(firstName, user);
		return user;
	}

	
	
	public User getUserByName(String firstName) {
		return nameIndex.get(firstName);
	}

	public User getUser(Long id) {
		return userIndex.get(id);
	}

	public void deleteUser(Long id) {
		User user = userIndex.remove(id);
		nameIndex.remove(user.firstName);
	}

	
	
	public Rating createRating(Long id, Long userID, Long movieID, int userRating) {
		Rating rating = null;
		Optional<User> user = Optional.fromNullable(userIndex.get(id));
		if (user.isPresent()) {
			rating = new Rating(userID, movieID, userRating);
			user.get().ratings.put(rating.id, rating);
			ratingsIndex.put(rating.id, rating);
		}
		return rating;
	}

	public Rating getRating(Long id) {
		return ratingsIndex.get(id);
	}

	
	public void addMovie(Long id, String title, String year, String url) {
		Optional<Rating> rating = Optional.fromNullable(ratingsIndex.get(id));
		if (rating.isPresent()) {
			rating.get().rating.add(new Movie(title, year, url));
		}
	}
	
	
	
	
	  @SuppressWarnings("unchecked")
	  void load(File file) throws Exception
	  {
	    ObjectInputStream is = null;
	    try
	    {
	      XStream xstream = new XStream(new DomDriver());
	      is = xstream.createObjectInputStream(new FileReader(file));
	      userIndex       = (Map<Long, User>)     is.readObject();
	      nameIndex      = (Map<String, User>)   is.readObject();
	      ratingsIndex = (Map<Long, Rating>) is.readObject();
	    }
	    finally
	    {
	      if (is != null)
	      {
	        is.close();
	      }
	    }
	  }

	  void store(File file) throws Exception
	  {
	    XStream xstream = new XStream(new DomDriver());
	    ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(file));
	    out.writeObject(userIndex);
	    out.writeObject(nameIndex);
	    out.writeObject(ratingsIndex);
	    out.close(); 
	  }
}
