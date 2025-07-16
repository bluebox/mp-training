package Practice.july7_streams;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ToUpperCase {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		List<String> fruits=Arrays.asList("apple","banana","grapes","mango");
		List<String> uppercaseFruits=fruits.stream().map((String fruit)->fruit.toUpperCase()).collect(Collectors.toList());
		System.out.println(uppercaseFruits);
		
		sc.close();
	}
}
