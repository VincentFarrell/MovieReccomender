package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Objects;

public class Rating {
	static Long counter = 0l;
	public Long id;
	public Long userID;
	public Long movieID;
	public Double userRating;

	public List<Movie> rating = new ArrayList<>();

	public Rating(Long userID, Long movieID, Double userRating) {
		this.id = counter++;
		this.userID = userID;
		this.movieID = movieID;
		this.userRating = userRating;
	}

	@Override
	public String toString() {
		return toStringHelper(this).addValue(id).addValue(userID).addValue(movieID).addValue(userRating).toString();
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.id, this.userID, this.movieID, this.userRating);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Rating) {
			final Rating other = (Rating) obj;
			return Objects.equal(userID, other.userID)
				&& Objects.equal(movieID, other.movieID)
				&& Objects.equal(userRating, other.userRating);

		} else {
			return false;
		}
	}
}