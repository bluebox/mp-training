package com.dailybasics;

import java.util.Collections;
import java.util.stream.Collector;
import java.util.stream.Stream;

public class Streams {

	public static void main(String[] args) {
		var stream = Stream.iterate(0, i->i+1)
				.limit(15)
				.filter(i->i%2!=0)
				.forEach(System.out::println);
				}
		

	}

