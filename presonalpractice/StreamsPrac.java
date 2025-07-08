package dev.tulasidhar.presonalpractice;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class StreamsPrac {
	public static void main(String[] args) {
		Stream<Integer> stream;
		List<Integer> arr = Arrays.asList(90,3,2,1,4);
		
		stream = arr.stream();
		
		stream
			.sorted()							//intermediate, this gives another result stream to work on
			.forEach(System.out::println);		//terminal , this gives output
		
		System.out.println("-".repeat(10));
		
		stream = arr.stream();
		
		Function<Integer,Integer> func = new Function<Integer,Integer>(){
			public Integer apply(Integer num){
				return num*2;
			}
		};
		
		stream.map(func)
			.forEach(System.out::println);
		
	}
	
	public static void doSomething(int a) {
		System.out.println("this is an integer = "+a);
	}
}

