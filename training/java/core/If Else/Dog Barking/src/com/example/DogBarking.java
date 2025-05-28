package com.example;

public class DogBarking {
	public static boolean shouldWakeUp(boolean barking,int time) {
		if((barking==true)&&(time>=0)&&(time<=23)&&((time<8)||(time>=22))) {
			return true;
		}
		else {
			return false;
		}
	}
	public static void main(String arg[]) {
		System.out.println(shouldWakeUp(true,21));
	}
}
