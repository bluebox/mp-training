package com.dailybasics;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {
	public static void main(String[] args) {
		// 7 idd numbers
		List<Integer> numbers = Stream.iterate(0, i -> i + 1).filter(i -> i % 2 != 0).limit(7)
				.collect(Collectors.toList());
		System.out.println(numbers);
		//using for each
		Stream.iterate(0, n -> n + 1).filter(n -> n % 2 == 0).limit(100).forEach(System.out::println);
		
		//mapping
		
		List<Integer> doubled = numbers.stream()
				.map(n -> n*2)
				.collect(Collectors.toList());
		System.out.println(doubled);
	}
}