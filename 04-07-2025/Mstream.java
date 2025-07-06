package com.prana;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Mstream {
	public static void main(String[] args) {
	     
    	String[] st1 = {"One","Two","Five"};
    	Arrays.stream(st1)
    	    .sorted(Comparator.reverseOrder())
            .forEach(System.out::println);
    	
    	String[] st2 = {"Six","Seven","Nine"};
    	Arrays.stream(st2)
    	    .sorted(Comparator.reverseOrder())
            .forEach(System.out::println);
    	
    	
    	Stream<String> s2 = Stream.of("Six","Seven","Nine");
    	Stream<String> s3 = Stream.of("Six","Seven","Nine");
    	Stream.concat(s2, s3)
	     .forEach(System.out::println);
    	
    	Stream<String> r = Stream.of("river","sky","water");
	
        IntStream.rangeClosed(1, 1000)
            .limit(20)
            .forEach(System.out::println); 
        
    }
}
