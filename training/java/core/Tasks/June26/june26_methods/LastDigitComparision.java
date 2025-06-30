package corejava.june26_methods;

import java.util.Scanner;

public class LastDigitComparision {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter any three number to compare the last digits");
		System.out.println("Enter the first number");
		int firstNumber=sc.nextInt();
		System.out.println("Enter the second number");
		int secondNumber=sc.nextInt();
		System.out.println("Enter the third number");
		int thirdNumber=sc.nextInt();
		if(hasSameLastDigit(firstNumber,secondNumber,thirdNumber)) {
			System.out.println("Atlest two numbers has same last digit");
		}
		else {
			System.out.println("No two numbers is having the same last digit or you enetered an invalid input");
		}
		sc.close();
	}
	public static boolean hasSameLastDigit(int firstNumber,int secondNumber, int thirdNumber) {
		if(isValid(firstNumber) && isValid(secondNumber) && isValid(secondNumber) ) {
					if(firstNumber%10==secondNumber%10 || firstNumber%10==thirdNumber%10 || secondNumber%10==thirdNumber%10) {
						return true;
					}		
		}
		return false;
	}
	
	public static boolean isValid(int number) {
		return (number>=10 && number<=1000)?  true: false;
	}

}
