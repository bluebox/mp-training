package Streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class Basics {

	public static void main(String[] args) {

		List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

		int sum = nums.stream().filter(num -> num % 2 == 0).mapToInt(Integer::intValue).sum();
		System.out.println("1.Sum of Evens: " + sum);
		System.out.println();

		List<String> words = Arrays.asList("computer", "mouse", "cpu", "keyboard", "desktop", "mouse", "cpu");

		long count = words.stream().filter(str -> str.length() > 5).count();
		System.out.println("2.Words greater than specified length: " + count);
		System.out.println();

		List<Integer> squares = nums.stream().map(num -> num * num).collect(Collectors.toList());
		System.out.println("3.Squares of numbers: " + squares);
		System.out.println();

		long maxi = nums.stream().mapToInt(Integer::intValue).max().getAsInt();
		System.out.println("4.Maximumof array: " + maxi);
		System.out.println();

		String res = words.stream().collect(Collectors.joining(" "));
		System.out.println("5.Joining list of strings: " + res);
		System.out.println();

		List<String> caps = words.stream().map(String::toUpperCase).sorted().collect(Collectors.toList());
		System.out.println("6.Capitalist and sort: " + caps);
		System.out.println();

		Double average = nums.stream().mapToInt(Integer::intValue).average().getAsDouble();
		System.out.println("7.Average of list of numbers: " + average);
		System.out.println();

		List<String> unique = words.stream().distinct().collect(Collectors.toList());
		System.out.println("8.Remove dupluicates: " + unique);
		System.out.println();

		boolean isEven = nums.stream().allMatch(n -> n % 2 == 0);
		if (isEven)
			System.out.println("9.All are even");
		else
			System.out.println("9.All are not even");
		System.out.println();

		String longestWord = words.stream().
				max(Comparator.comparingInt(String::length))
				.orElse(null);
		System.out.println("10.Longest word: " + longestWord);

	}
}
