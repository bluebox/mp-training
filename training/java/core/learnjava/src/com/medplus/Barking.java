package com.medplus;

public class Barking {
	Barking(boolean bark,int hour){
		System.out.println("Shoould wake up "+((bark && (hour>22 || hour<8)) ?true:false));
	}


public static void main(String[] args) {
	Barking barking = new Barking(true,8);
}
}