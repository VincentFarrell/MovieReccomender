package models;

public class Fixtures {
	public static User[] users = {
			new User("homer", "simpson", "male",  "unemployed", 40),
			new User("marge", "simpson", "female", "unemployed", 35),
			new User("lisa", "simpson", "female", "unemployed", 11),
			new User("bart", "simpson", "male", "unemployed", 10),
			new User("maggie", "simpson", "female", "unemployed", 3)
	};

	public static Rating[] ratings =
	{
	  new Rating (0L, 0L, 8.5),
	  new Rating (0L, 2L, 9.5),
	  new Rating (1L, 0L, 7.5),
	  new Rating (3L, 3L, 9.0),
	  new Rating (2L, 1L, 8.5)
	};
	
	public static Movie[]Movies =
	{
	  new Movie("The Matrix", "1999", "http://www.imdb.com/title/tt0133093/"),
	  new Movie("Nightcrawler", "2014", "http://www.imdb.com/title/tt2872718/"),  
	  new Movie("Alien", "1979", "http://www.imdb.com/title/tt0078748/"),
	  new Movie("John Wick", "2014", "http://www.imdb.com/title/tt2911666/")       
	};
}