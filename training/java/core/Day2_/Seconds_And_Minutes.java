package com.Day2_;

public class Seconds_And_Minutes {
	public static void main(String[]args) {
		System.out.println(time1(35000));
		System.out.println(time(400,600));
		System.out.println(time(-400, -61));
	}
	public static String time1(int seconds) {
		if (seconds < 0) {
			return"Invalid time for Seconds";
		}
		
		int minutes = seconds / 60;
		return time(minutes, seconds);
	}
	public static String time(int minutes, int seconds) {
		if (minutes < 0) {
			return"Invalid time for Minutes";
		}
		int hours = minutes / 60;
		int remainingmin = minutes % 60;
		int remainingsec = seconds % 60;
		return hours + "h" + remainingmin + "m" + remainingsec + "s";
	}
	
}
