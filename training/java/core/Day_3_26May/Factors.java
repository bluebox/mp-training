package Day_3_26May;

import java.util.*;

public class Factors {
	public static void printFactors(int input) {
		System.out.println("Factors of " + input + " are :");
		for (int i = 1; i < input; i++) {
			if (input % i == 0)
				System.out.println(i + " ");
		}
	}

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		int input = sc.nextInt();

		printFactors(input);
		sc.close();
	}

}
