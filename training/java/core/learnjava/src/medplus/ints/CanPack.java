package medplus.ints;

import java.util.Scanner;

public class CanPack {
	public static boolean canPack(int bigCount,int smallCount,int goal) {
		if (bigCount <0 || smallCount <0 || goal <0) return false;
		if (goal-(bigCount*5)<0) goal = goal%5;
		else goal -= (bigCount*5);
		
		if (goal <= smallCount)return true;
		return false;
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter values of bigCount , smallCount , goal ");
		int bigCount = scanner.nextInt();
		int smallCount = scanner.nextInt();
		int goal = scanner.nextInt();
		
		System.out.println("can we reach goal : "+canPack(bigCount,smallCount,goal));
		scanner.close();
	}

}
