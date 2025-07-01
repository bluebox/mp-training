package ComparableAndComparator;

import java.util.ArrayList;
import java.util.Collections;

class Movie implements Comparable<Movie>{
	private String name;
	private double rating;
	private int year;
	
	
	public Movie(String name, double rating, int year) {
		this.name = name;
		this.rating = rating;
		this.year = year;
	}

	@Override
	public int compareTo(Movie o) {
		// TODO Auto-generated method stub
		return this.year-o.year;
	}

	public String getName() {
		return name;
	}

	public double getRating() {
		return rating;
	}

	public int getYear() {
		return year;
	}

	@Override
	public String toString() {
		return "Movie [name=" + name + ", rating=" + rating + ", year=" + year + "]";
	}
	

	
}

public class ComparableExample {
	
	public static void main(String[] args) {
		
		ArrayList<Movie> movie=new ArrayList<>();
		
		movie.add(new Movie("RRR",9.5,2023));
		movie.add(new Movie("BB1",8.1,2019));
		movie.add(new Movie("BB2",8.5,2021));
		movie.add(new Movie("YMD",8.0,2016));
		movie.add(new Movie("SNO",7.5,2004));
		
		Collections.sort(movie);
		
		for(Movie m: movie) {
			System.out.println(m.toString());
		}

		
	}

}
