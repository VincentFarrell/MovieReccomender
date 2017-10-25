package controllers;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import utils.FileLogger;
import models.User;


public class Main
{
  public static void main(String[] args) throws IOException
  {    
    MovieReccomenderAPI moviereccomenderAPI = new MovieReccomenderAPI();

    moviereccomenderAPI.createUser("Bart",  "Simpson", "Male",  "Unemployed", 10);
    moviereccomenderAPI.createUser("Homer", "Simpson", "Male", "Unemployed", 40);
    moviereccomenderAPI.createUser("Lisa",  "Simpson", "Female", "Unemployed", 11);

    List<User> users = moviereccomenderAPI.getUsers();
    System.out.println(users);
  }
}