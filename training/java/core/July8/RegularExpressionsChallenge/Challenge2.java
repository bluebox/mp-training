package July8.RegularExpressionsChallenge;

import java.util.Scanner;

public class Challenge2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner (System.in);
		String input = sc.nextLine();
		System.out.println(input.matches("^[A-Z][\\w ]+[.]$"));
		sc.close();
		
	}
}
