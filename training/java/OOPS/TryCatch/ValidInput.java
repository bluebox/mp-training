package TryCatch;

import java.util.Scanner;

public class ValidInput {

	public static void main(String[] args) {
		int sum = 0, count = 1, value;
		Scanner sc = new Scanner(System.in);
		do {
			try {
				System.out.println("Enter input #" + count + ":");
				String input = sc.nextLine();
				value = Integer.parseInt(input);
				count++;
				sum+=value;

			} catch (NumberFormatException e) {
				System.out.println("invalid input");
			}
		} while (count <= 5);
		System.out.println("the sum of the 5 numbers is :"+sum);

	}

}
