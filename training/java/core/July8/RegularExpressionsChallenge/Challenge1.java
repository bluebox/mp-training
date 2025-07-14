package July8.RegularExpressionsChallenge;

import java.util.Scanner;

public class Challenge1 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String a = "Hello, World!";
		String b = sc.nextLine();
		
		System.out.println(a.matches(b));
		sc.close();
	}
}
