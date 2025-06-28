package June26;

import java.util.Scanner;

public class ReadingUserInput {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int i = 1;
		int sum = 0;
		while (i <= 5) {
			try {
				System.out.print("Enter a valid number : ");
				String userInput = sc.next();
				int validNum = Integer.parseInt(userInput);
				System.out.println("You entered " + validNum);
				sum += validNum;
				i++;
			}catch(NumberFormatException e) {
				System.out.println("Invalid Number");
			}
		}
		System.out.println("Sum of 5 numbers is " + sum);
		sc.close();
	}
}
