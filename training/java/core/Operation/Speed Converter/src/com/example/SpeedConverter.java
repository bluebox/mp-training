package com.example;

import java.util.Scanner;

public class SpeedConverter {
	public static long toMilePerHour(double speed) {
		double kmph=speed*0.621371;
		if(kmph<0) {
			return -1;
		}
		else {
			return Math.round(kmph);
		}
	}
	public static void main(String arg[]) {
		Scanner sc=new Scanner(System.in);
		System.out.println("The speed before rounding off is:");
		System.out.println("The speed after round off is:"+toMilePerHour(sc.nextDouble()));
	}
}
