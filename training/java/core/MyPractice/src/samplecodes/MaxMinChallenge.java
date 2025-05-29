package samplecodes;

import java.util.Scanner;

public class MaxMinChallenge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getMinMax();
	}
	public static void getMinMax() {
		int min=Integer.MAX_VALUE;
		int max=Integer.MIN_VALUE;
		Scanner sc=new Scanner(System.in);
		while(true) {
			System.out.println("Enter number: to continue and Enter char to quit");
			try {
				int a=sc.nextInt();
				min=Math.min(min,a);
				max=Math.max(max, a);
			}
			catch(Exception e) {
				break;
			}
		}
		System.out.println("Minimum value is "+min +" and the max value is "+max);
		sc.close();
	}

}
