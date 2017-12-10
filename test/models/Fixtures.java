package models;

public class Fixtures {
	public static User[] users = {
			new User("homer", "simpson", "m",  "unemployed", 40, "admin"),
			new User("marge", "simpson", "f", "unemployed", 35, "admin"),
			new User("lisa", "simpson", "f", "unemployed", 11, "default"),
			new User("bart", "simpson", "m", "unemployed", 10, "default"),
			new User("maggie", "simpson", "f", "unemployed", 3, "default")
	};

	public static Rating[] ratings =
	{
	  new Rating (0L, 0L, 3),
	  new Rating (0L, 2L, 4),
	  new Rating (1L, 0L, 5),
	  new Rating (3L, 3L, 3),
	  new Rating (2L, 1L, 2)
	};
	
	public static Movie[] movies =
	{
	  new Movie("The Matrix", "1999", "http://www.imdb.com/title/tt0133093/"),
	  new Movie("Nightcrawler", "2014", "http://www.imdb.com/title/tt2872718/"),  
	  new Movie("Alien", "1979", "http://www.imdb.com/title/tt0078748/"),
	  new Movie("John Wick", "2014", "http://www.imdb.com/title/tt2911666/")       
	};
}