package day_4_7_2025.streams;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Streams {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
       // challenge-1
		int seed=1;
		var streamB=Stream.iterate(seed, i->i<=15,i->i+1)
				    .filter(i->i%1==0)
				    .map(i->"B - "+i);
		
		var StreamI=Stream.iterate(seed+15,i->i<=30, i->i=i+1)
				.map(i->"I "+"- "+i);
		
		var streamBI=Stream.concat(streamB,StreamI);
		
		var StreamN=Stream.iterate(seed+30,i->i<=45, i->i=i+1)
				.map(i->"N "+"- "+i);
		
		var streamBIN=Stream.concat(streamBI,StreamN);
		
		streamBIN.forEach(System.out::println);
		
		//
		
		 IntStream stream = IntStream.rangeClosed(0, 11)
				 .sorted();
         stream.forEachOrdered(System.out::println);
	}

}
