package Streams;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExample {

	public static void main(String[] args) {
		String[] strings = {"one", "Two","Three"};
		Arrays.stream(strings)
			  .sorted(Comparator.reverseOrder())
			  .forEach(System.out::println);
		
		System.out.println();
		
		var s=Stream.of("one", "Two","Three").map(String::toUpperCase);
//			  .forEach(System.out::println);
		
		var s1= Stream.of("Four", "Five","Six")
		  .map(String::toUpperCase);
//		  .forEach(System.out::println);
		
		Stream.concat(s,s1)
		.map(st ->st.charAt(0)+"-"+st)
		.forEach(System.out::println);
		
		System.out.println();

		LinkedHashMap<Character, int[]> myMap= new LinkedHashMap<>();
		
		int bingidx=1;
		
		for(char c: "BINGO".toCharArray()) {
			int[] numbers =new int[15];
			int labelNo =bingidx;
			Arrays.setAll(numbers,i->i+labelNo);
			myMap.put(c, numbers);
			bingidx+=15;
		}
		
		myMap.entrySet().stream()
		.map(e -> e.getKey() + " has range: "+e.getValue()[0]+" - "+
			e.getValue()[e.getValue().length-1])
		.forEach(System.out::println);
		
		
	var arr = IntStream.iterate(1, i-> i<=20,i->i+1).summaryStatistics();;
	System.out.println(arr);
		
		
	}

}

