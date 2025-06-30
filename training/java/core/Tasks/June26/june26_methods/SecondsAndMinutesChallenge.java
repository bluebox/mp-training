package corejava.june26_methods;

import java.util.Scanner;

public class SecondsAndMinutesChallenge {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the tome in seconds to get in HH:MM:SS format");
		int seconds=sc.nextInt();
		time(seconds);
		sc.close();
	}
	public static void time(int seconds) {
		int minutes=seconds/60;
		time(minutes,seconds);
	}
	public static void time(int minutes, int seconds) {
		int hours=minutes/60;
		int remainingMinutes=minutes%60;
		int remainingSeconds=seconds%60;
		System.out.println(hours+"H:"+remainingMinutes+"M:"+remainingSeconds+"S");
	}

}
