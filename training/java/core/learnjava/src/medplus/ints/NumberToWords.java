package medplus.ints;

import java.util.Scanner;

public class NumberToWords {
	public static void numberToWords(int value) {
		String number = ""+value;
		for(int i = 0;i<number.length();i++) {
			switch (number.charAt(i)) {
			case '1' -> System.out.print("one ");
			case '2' -> System.out.print("two ");
			case '3' -> System.out.print("three ");
			case '4' -> System.out.print("four ");
			case '5' -> System.out.print("five ");
			case '6' -> System.out.print("six ");
			case '7' -> System.out.print("seven ");
			case '8' -> System.out.print("eight ");
			case '9' -> System.out.print("nine ");
			case '0' -> System.out.print("zero ");
			}
		}
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a number : ");
		int value = scanner.nextInt();
		numberToWords(value);
		scanner.close();
		
	}
}
