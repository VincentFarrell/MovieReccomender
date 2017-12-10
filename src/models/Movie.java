package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Objects;


public class Movie {
	//Initialise variables for Movie
	static Long counter = 0l;
	public Long id;
	public String title;
	public String year;
	public String url;
	
	public Map<Long, Rating> ratings = new HashMap<>();

	//constructors for Movie
	public Movie(String title, String year, String url) {
		this.id = counter++;
		this.title = title;
		this.year = year;
		this.url = url;
	}
	
	public Movie(Long id, String title, String year, String url) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.url = url;
	}

	
	@Override
	  public String toString()
	  {
	    return toStringHelper(this).addValue(id)
	                               .addValue(title)
	                               .addValue(year)
	                               .addValue(url)
	                               .toString();
	  }


	@Override
	public int hashCode() {
		return Objects.hashCode(this.id, this.title, this.year, this.url);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Movie) {
			final Movie other = (Movie) obj;
			return Objects.equal(title, other.title)
				&& Objects.equal(year, other.year)
				&& Objects.equal(url, other.url);

		} else {
			return false;
		}
	}

}