package ComparableAndComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Movies {
	private String name;
	private double rating;
	private int year;
	
	
	public Movies(String name, double rating, int year) {
		this.name = name;
		this.rating = rating;
		this.year = year;
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

class Rating implements Comparator<Movies>{

	@Override
	public int compare(Movies o1, Movies o2) {
		// TODO Auto-generated method stub
		return Double.compare(o1.getRating(), o2.getRating());
	}
	
}

class Name implements Comparator<Movies>{

	@Override
	public int compare(Movies o1, Movies o2) {
		// TODO Auto-generated method stub
		return o1.getName().compareTo(o2.getName());
	}
	
}

public class ComparatorExample {

	public static void main(String[] args) {
		
		ArrayList<Movies> movie=new ArrayList<>();
		
		movie.add(new Movies("RRR",9.5,2023));
		movie.add(new Movies("BB1",8.1,2019));
		movie.add(new Movies("BB2",8.5,2021));
		movie.add(new Movies("YMD",8.0,2016));
		movie.add(new Movies("SNO",7.5,2004));
		
	
		Collections.sort(movie,new Rating());
		System.out.println("Movies sorted by Rating: ");
		for(Movies m: movie) {
			System.out.println(m.toString());
		}
		
		Collections.sort(movie,new Name());
		System.out.println("Movies sorted by Name: ");
		for(Movies m: movie) {
			System.out.println(m.toString());
		}
		


	}

}
