package com.example;

import java.util.stream.IntStream;

public class Bingo {
	public static void main(String[] args) {
		IntStream.iterate(0, i->i<=75,i->++i)
		.forEach(i->System.out.printf("%s-%d\n",(i<16)?'B':(i<=30)?'I':(i<=45)?'N':(i<=60)?'G':'O',i));
	}
}
