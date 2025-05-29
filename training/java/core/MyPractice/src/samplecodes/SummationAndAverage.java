package samplecodes;

import java.util.Scanner;

public class SummationAndAverage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		inputThenPrintSumAndAverage();

	}
	public static void inputThenPrintSumAndAverage() {
		Scanner sc=new Scanner(System.in);
		int sum=0;
		int cnt=0;
		while(true){
			System.out.println("Enter number: to continue and Enter char to quit");
			try {
				int a=sc.nextInt();
				sum+=a;
				cnt++;
			}
			catch(Exception e) {
				break;
			}
		}
		System.out.println("SUM = "+sum +" AVG = "+(cnt==0) != null?0:sum/cnt);
		
	}

}
