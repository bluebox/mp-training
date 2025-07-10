package June26;
import java.util.Scanner;

public class StarPattern {
	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		System.out.print("Enter a number : ");
		int number = sc.nextInt();
		printSquareStar(number);
		sc.close();
	}
	public static void printSquareStar(int number) {
		if(number < 5) System.out.println("Invalid Value");
		else {
		for(int i = 1; i <= number; i++) {
			for(int j= 1; j <= number; j++) {
				if (i == 1 || i == number || i == j || j == number-i+1) System.out.print("*");
				else System.out.print(" ");
			}
			System.out.println("");
		}}
	}
}
