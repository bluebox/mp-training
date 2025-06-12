package Methods;

import java.util.Scanner;

public class SecondsConversionMethod {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int seconds = scanner.nextInt();
		secondsConversion(seconds);
		scanner.close();
	}
	public static void secondsConversion(int seconds) {
		int minutes = seconds/60;
		int hours = minutes/60;
		int remainingMinutes = minutes%60;
		int remainingSeconds = seconds%60;
		System.out.println(seconds+" seconds is equal to: "+hours+" h "+remainingMinutes+" m "+remainingSeconds+" s");
	}
}
