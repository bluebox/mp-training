package training_java.May27;

import java.util.Scanner;

public class SumAndAvg {
	public static void inputThenPrintSumAndAverage()
	{
		Scanner sc= new Scanner(System.in);
		int sum=0;
		double avg=0;
		int count=0;
		System.out.println("enter :");
		while(true) {
			if(!sc.hasNextInt()) {
				break;
			}
			sum+=sc.nextInt();
			count+=1;
			
		}
		sc.close();
		if(count>0) {
		avg=Math.round(sum/count);
		}
		System.out.println(" sum :" + sum+" average: "+ avg+"counter "+ count);
	}
	public static void main(String[] args) {
		inputThenPrintSumAndAverage();
	}
}
