package Strems;

import java.util.Arrays;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StremMain {
	
	    public static void main(String[] args) {
	        
   		
	    	String arr[]=new String[15];
	    	for(int i=0;i<15;i++) {
	    		arr[i]="B"+(i+1);
	    	}
	    	Stream<String> forB=Arrays.stream(arr);
	        
	    	
	    	
	        List<String> forI = IntStream.range(16, 31)
	                .mapToObj(i -> "I" + i)
	                .collect(Collectors.toList());
	        Stream<String> iStream = forI.stream();
	
	        Stream<String> forN = Stream.iterate(31, i -> i + 1)
	                .limit(15)
	                .map(i -> "N" + i);

	        Stream<String> forG = IntStream.rangeClosed(46, 60)
	                .mapToObj(i -> "G" + i);

	        Stream<String> forO = Stream.iterate(61, i -> i + 1)
	                .limit(15)
	                .map(i -> "O" + i);

	        Stream.concat(
	            Stream.concat(
	                Stream.concat(
	                    Stream.concat(forB, iStream), 
	                    forN),
	                forG),
	            forO)
	        .forEach(System.out::println);
	    }
	}
	 
 
