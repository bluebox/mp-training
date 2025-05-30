package loops;

import java.util.Scanner;

public class CalculateSumAndAverage {
	public static void main(String [] args) {
		inputThenPrintSumAndAverage();
	}
		public static void inputThenPrintSumAndAverage() {
		Scanner sc = new Scanner(System.in);
		int i=0;
		int sum=0;
		while(true) {
			System.out.print("Enter number #"+ (i+1) + ":");
			String myVariable = sc.nextLine();
			if((myVariable.charAt(0)) >  47 && (myVariable.charAt(0))<58) {
				sum += (myVariable.charAt(0)) - 48;
				i++;
			}
			else
				break;
		}
		System.out.println("sum is :"+ sum + " and avg is: "+(double)(sum)/(double)(i));
	}

}
