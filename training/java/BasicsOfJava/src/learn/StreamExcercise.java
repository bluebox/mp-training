package learn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExcercise {
	
	public static void main(String[] args) throws Exception{
		
		List<Integer> list = Arrays.asList(99,12,37,43,56,74);
		
		double average = list.stream()
				.mapToDouble(Integer::doubleValue).sum();
		
		System.out.println(average);
		
		List<String> list2 = Arrays.asList("Hello","world","Medplus","Hii","work","Mphs");
		
		List<String> list2Updated = list2.stream()
				.map(s -> s.toLowerCase() )
				.collect(Collectors.toList());
		list2Updated.forEach(System.out::println);
		
		list2.stream()
			.map(s->s.toUpperCase())
			.forEach(System.out::println);
		
		list.stream()
			.filter(s -> s%2==0 && s>50)
			.forEach(System.out::println);
		
		int sumOfEvenNumber = list.stream()
				.filter(s -> s%2 == 0)
				.mapToInt(Integer::intValue)
				.sum();
		System.out.println("sum of Even Numbers "+sumOfEvenNumber);
		
		int sumOfOddNumbers = list.stream()
				.filter(s -> s%2 == 1)
				.mapToInt(Integer::intValue)
				.sum();
		
		System.out.println("sum of Odd Numbers "+sumOfOddNumbers);
		
		List < Integer > nums = Arrays.asList(10, 23, 22, 23, 24, 24, 33, 33, 22, 15, 26, 15);
		
		List < Integer > nums1 = nums.stream()
				.distinct()
				.collect(Collectors.toList());
		
		list2.stream()
			.filter(s -> s.toLowerCase().contains("h"))
			.forEach(System.out::println);
		System.out.println("_______________");
		
		list2.stream()
		.filter(s -> s.toLowerCase().startsWith("h"))
		.forEach(System.out::println);
		
		
		List < String > colors = Arrays.asList("Red", "Green", "Blue", "Pink", "Brown");
		
		colors.stream()
			.sorted(Comparator.naturalOrder())
			.forEach(System.out::println);
		

		System.out.println("__________________");
		
		colors.stream()
			.sorted(Comparator.reverseOrder())
			.collect(Collectors.toList())
			.forEach(System.out::println);
		
		System.out.println("__________________");
		
		int minNumber = list.stream()
				.min(Integer::compare)
				.orElse(null);
		System.out.println(minNumber);
		
		int maxNumber = list.stream()
				.max(Integer::compare)
				.orElse(null);
		System.out.println(maxNumber);
		
		int secondMin = list.stream()
				.sorted((a,b) -> a.compareTo(b))
				.skip(1)
				.findFirst()
				.orElse(null);
		System.out.println(secondMin);
		
		int secondMax = list.stream()
				.sorted((a,b) -> b.compareTo(a))
				.skip(1)
				.findFirst()
				.orElse(null);
		System.out.println(secondMax);
		
		
		
	}
}
