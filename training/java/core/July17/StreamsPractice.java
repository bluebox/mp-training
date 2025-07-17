package July17.StreamsPractice;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class StreamsPractice {
	public static void main(String[] args) {

		double avg = IntStream.range(1, 17).average().orElse(0);
		System.out.println("Average = " + avg);
		
		int even_sum = IntStream.range(1, 17).filter(x -> x%2==0).sum();
		int odd_sum = IntStream.range(1, 17).filter(x -> x%2!=0).sum();
		System.out.println("Even Sum = " + even_sum + " Odd Sum = " + odd_sum);
		
		List<Integer> nums = List.of(10, 23, 22, 23, 24, 24, 33, 15, 26, 15);
		nums.stream().distinct().forEach(System.out::println);
		
		int min_value = nums.stream().min(Integer::compare).orElse(null);
		int max_value = nums.stream().max(Integer::compare).orElse(null);
		System.out.println("Min Value = " + min_value + " Max Value = " + max_value);
		
		int second_min = nums.stream().sorted().skip(1).findFirst().orElse(null);
		int second_max = nums.stream().sorted((a,b) -> Integer.compare(b, a)).skip(1).findFirst().orElse(null);
		System.out.println("Second Min Value = " + second_min + " Second Max Value = " + second_max);
		
		List<String> color = List.of("red", "blue", "black");
		List<String> Ucolor = color.stream().map(x -> x.toUpperCase()).toList();
		System.out.println(Ucolor);
		Ucolor.stream().map(String::toLowerCase).forEach(System.out::println);
		
		color.stream().filter(x -> x.startsWith("b")).forEach(System.out::println);
		
		color.stream().sorted().forEach(System.out::println);
		color.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);

	}
}