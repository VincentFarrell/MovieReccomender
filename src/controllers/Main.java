package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.junit.Test;

import com.google.common.base.Optional;

import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellDependent;
import asg.cliche.ShellFactory;
import models.Movie;
import models.User;
import utils.Serializer;
import utils.XMLSerializer;

public class Main  implements ShellDependent {
	
	private static final String ADMIN = "admin";
	public MovieReccomenderAPI movieApi;
	private Shell theShell;

	  public Main() throws Exception {
		    File datastore = new File("datastore4.xml");
		    Serializer serializer = new XMLSerializer(datastore);
		    movieApi = new MovieReccomenderAPI(serializer);
		    if (datastore.isFile()) {
		     movieApi.load();
		    }
		  }

	  
	  public static void main(String[] args) throws Exception {
		    Main main = new Main();
		    main.movieApi.initalLoad();
		    Shell shell = ShellFactory.createConsoleShell("cm", "Welcome to MovieReccomender Console - ?help for instructions",
		        main);
		    shell.commandLoop();
		    main.movieApi.store();
		  }
	

	@Override
	public void cliSetShell(Shell theShell) {
		this.theShell = theShell;
		
	}
	
	@Command(description = "Log in")
	  public void logIn(@Param(name = "user-id") Long userID, @Param(name = "password") String password)
	      throws IOException {

	    if (movieApi.login(userID, password) && movieApi.currentUser.isPresent()) {
	      User user = movieApi.currentUser.get();
	      System.out.println("You are logged in as " + user.firstName);
	      if (user.role!=null && user.role.equals(ADMIN)) {
	        AdminMenu adminMenu = new AdminMenu(movieApi, user.firstName);
	        ShellFactory.createSubshell(user.firstName, theShell, "Admin", adminMenu).commandLoop();
	      } else {
	        DefaultMenu defaultMenu = new DefaultMenu(movieApi, user);
	        ShellFactory.createSubshell(user.firstName, theShell, "Default", defaultMenu).commandLoop();
	      }
	    } else
	      System.out.println("Unknown username/password.");
	  }
	
	
	
	
	


}