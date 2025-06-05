package myStreams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MyStream {
	public static void main(String[] args) {
		List<String> strings = new ArrayList<String>();
		strings.add("One");
		strings.add("Two");
		strings.add("Three");
		strings.add("Four");
		strings.add("Five");
		System.out.println("Streams");
		Stream firstStream= strings.stream()
				.map(s -> s.toUpperCase())
				.filter(s -> s.charAt(0)=='T');
		Stream secondStream=strings.stream()
		.map(s -> s.toUpperCase())
		.filter(s -> s.charAt(0)=='F');
		Stream resultStream = Stream.concat(firstStream, secondStream);
		System.out.println(resultStream
		.sorted()
		.count());
		resultStream
		.sorted()
		.forEach(s -> System.out.println(s));
				
	}

}
