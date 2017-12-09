package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import com.google.common.base.Objects;

import utils.ToJsonString;

public class Movie {
	//initialize variables for Movie
	static Long counter = 0l;
	public Long id;
	public String title;
	public String year;
	public String url;

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
	    return new ToJsonString(getClass(), this).toString();
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

	
	//getters + setters for Movie
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}