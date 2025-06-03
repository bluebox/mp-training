package com.example;

import java.util.stream.IntStream;

public class MultiplesOf3Or5 {
	public static void main(String[] args) {
		IntStream.iterate(1, i->i+1)
		.filter(i->((i%3==0)||(i%5==0)))
		.limit(10)
		.forEach(System.out::println);
	}
}
