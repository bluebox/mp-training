package com.Day2_;

public class Year {
	public static void main(String[] args) {
		System.out.println(seconds(86400));
		System.out.println(seconds(100000));
		System.out.println(seconds(-500));
	}

	public static String seconds(int seconds) {
		if (seconds < 0) {
			return "Invalid time for Seconds";
		}
		return time(seconds);
	}

	public static String time(int seconds) {
		int days = seconds / 86400;
		int remainingAfterDays = seconds % 86400;

		int hours = remainingAfterDays / 3600;
		int remainingAfterHours = remainingAfterDays % 3600;

		int minutes = remainingAfterHours / 60;
		int remainingSeconds = remainingAfterHours % 60;

		return days + "d " + hours + "h " + minutes + "m " + remainingSeconds + "s";
	}
}
