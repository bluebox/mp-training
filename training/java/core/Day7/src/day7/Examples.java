package day7;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Examples {
	public static void main(String[] args) {
		List<Integer>num=Arrays.asList(1,2,3,4,5,6,7,8);
		List<Integer> even=num.stream()
				.filter(s->s%2==0)
				.collect(Collectors.toList());
		even.forEach(s->System.out.print(s+" "));
		
		
		List<String>nums=Arrays.asList("Zebra","lion","apple");
		nums.sort((str1,str2)->str1.compareToIgnoreCase(str2));
		nums.forEach(System.out::println);
		
		
		List<Double> numms=Arrays.asList(12.0,10.5,14.0,12.5);
		Double avg=numms.stream()
				.mapToDouble(Double::doubleValue)
				.average()
				.orElse(0.0);
		System.out.println(avg);
				
				
				
	}
}
