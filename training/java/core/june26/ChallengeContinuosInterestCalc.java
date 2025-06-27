package com.tulasidhar.june26;

public class ChallengeContinuosInterestCalc {
	public static void main(String[] args) {
		
		
		for (double rate = 7.5; rate <= 10 ; rate+=0.25) {
			double amount  = 100.00d;
			System.out.println("Interest for amount " + amount +" at interest rate " 
								+ rate + "% = " + calculateInterest(amount,rate));
		}
	}
	
	public static double calculateInterest(double amount,double interestRate) {
		return (amount * (interestRate) / 100);
	}
	
}
