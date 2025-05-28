package Main;

import java.util.Scanner;

public class SecondsConversion {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int seconds = scanner.nextInt();
		int minutes = seconds/60;
		int hours = minutes/60;
		int remainingMinutes = minutes%60;
		int remainingSeconds = seconds%60;
		System.out.println(seconds+" seconds is equal to: "+hours+" h "+remainingMinutes+" m "+remainingSeconds+" s");
		scanner.close();
	}
}
