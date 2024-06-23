package eu.ase.lab4;

public class Movie implements Cloneable {
	
	private int year;
	private String title;
	private float rating;
	
	public Movie(int year, String title, float rating) {
		super();
		this.year = year;
		this.title = title;
		this.rating = rating;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Movie clone = (Movie)super.clone();		// first creates a shallow copy
		clone.year = this.getYear();
		clone.title = new String(this.getTitle());
		clone.rating = this.getRating();
		return clone;
	}

	@Override
	public String toString() {
		return String.format("The movie %s from %d has a rating of %.1f", 
				this.getTitle(),
				this.getYear(),
				this.getRating());
	}
	
//	@Override
//	public int compareTo(Movie movieToCompare) {
//		
//		if (this.getRating() == movieToCompare.getRating()) {
//			return 0;
//		}
//		if (this.getRating() > movieToCompare.getRating()) {
//			return 1;
//		} else {
//			return -1;
//		}
//	}
}
