package corejava.june26_methods;

import java.util.Scanner;

public class WordRepresentationOfNumber {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a number to print its word representation");
		int number=sc.nextInt();
		printNumberInWord(number);
		sc.close();
	}

	public static void printNumberInWord(int number) {
		switch(number) {
		case 0:System.out.println("ZERO");
				break;
		case 1:System.out.println("ONE");
				break;
		case 2:System.out.println("TWO");
				break;
		case 3:System.out.println("THREE");
				break;
		case 4:System.out.println("FOUR");
				break;
		case 5:System.out.println("FIVE");
				break;
		case 6:System.out.println("SIX");
				break;
		case 7:System.out.println("SEVEN");
				break;
		case 8:System.out.println("EIGHT");
				break;
		case 9:System.out.println("NINE");
				break;
		default:System.out.println("OTHER");
		}
	}
}
