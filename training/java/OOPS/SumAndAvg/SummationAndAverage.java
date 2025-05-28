package SumAndAvg;

import java.util.Scanner;

public class SummationAndAverage {

	public static void main(String[] args) {
		inputThenPrintSumAndAverage();
	}
	public static void inputThenPrintSumAndAverage()
	{
		int value,sum=0,count=0;
		String str;
		Scanner sc= new Scanner(System.in);
		do {
				try 
				{
					System.out.println("Enter the input #"+(count+1));
					str=sc.nextLine();
					value=Integer.parseInt(str);
					sum+=value;
					count++;
				}
				catch(NumberFormatException e)
				{
					System.out.println("the sum of the numbers entered is:"+sum);
					System.out.println("the average of the numbers entered is:"+(sum/count));
					break;
				}
		}while(true);
	}


}
