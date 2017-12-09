package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Collection;

import org.junit.Test;

import com.google.common.base.Optional;

import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;
import models.Movie;
import models.User;
import utils.Serializer;
import utils.XMLSerializer;


public class Main
{
  public MovieReccomenderAPI movieApi;
  public MovieReccomenderAPI movies;

  public Main() throws Exception
  {
    File datastore = new File("datastore.xml");
    Serializer serializer = new XMLSerializer(datastore);

    movieApi = new MovieReccomenderAPI(serializer);
    if (datastore.isFile())
    {
      movieApi.load();
    }
  }

  public static void main(String[] args) throws Exception
  {
    Main main = new Main();

    Shell shell = ShellFactory.createConsoleShell("pm", "Welcome to pacemaker-console - ?help for instructions", main);
    shell.commandLoop();

    main.movieApi.store();
  }
  
  
  @Test
  public void testXMLSerializer() throws Exception
  { 
    String datastoreFile = "testdatastore.xml";
    //deleteFile (datastoreFile);

    Serializer serializer = new XMLSerializer(new File (datastoreFile));

    movies = new MovieReccomenderAPI(serializer);
    //populate(movies);
    movies.store();

    MovieReccomenderAPI movies2 =  new MovieReccomenderAPI(serializer);
    movies2.load();

    assertEquals (movies.getUsers().size(), movies2.getUsers().size());
    for (User user : movies.getUsers())
    {
      assertTrue (movies2.getUsers().contains(user));
    }
    //deleteFile ("testdatastore.xml");
  }
  
  
  @Command(description="Get all users details")
  public void getUsers ()
  {
    Collection<User> users = movieApi.getUsers();
    System.out.println(users);
  }
  
  @Command(description="Create a new User")
  public void createUser (@Param(name="first name") String firstName, @Param(name="last name") String lastName, 
                          @Param(name="gender")      String gender,     @Param(name="occupation")  String  occupation,
                          @Param(name="age") 		   int age)
  {
    movieApi.createUser(firstName, lastName, gender, occupation, age);
  }

  @Command(description="Get a Users detail")
  public void getUser (@Param(name="first name") String firstName)
  {
    User user = movieApi.getUserByName(firstName);
    System.out.println(user);
  }

  @Command(description="Delete a User")
  public void deleteUser (@Param(name="first name") String firstName)
  {
    Optional<User> user = Optional.fromNullable(movieApi.getUserByName(firstName));
    if (user.isPresent())
    {
      movieApi.deleteUser(user.get().id);
    }
  }
  
  
  @Command(description="Add an Rating")
  public void addRating (@Param(name="id")  Long   id,    @Param(name="user-id") Long userID, 
                           @Param(name="movie-id") Long movieID, @Param(name="user-rating") int userRating)
  {
    Optional<User> user = Optional.fromNullable(movieApi.getUser(id));
    if (user.isPresent())
    {
    	movieApi.createRating(id, userID, movieID, userRating);
    }
  }

  @Command(description="Add Rating to a Movie")
  public void addMovie (@Param(name="movie-id")  Long  id,        @Param(name="title")     String title,
                           @Param(name="year")  String year,  @Param(name="url") String URL)
  {
    Optional<Movie> movie = Optional.fromNullable(movieApi.getMovie(id));
    if (movie.isPresent())
    {
    	movieApi.addMovie(movie.get().id, title, year, URL);
    }
  }
}