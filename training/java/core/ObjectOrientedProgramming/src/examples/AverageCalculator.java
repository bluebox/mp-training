package examples;
import java.util.Scanner;

public class AverageCalculator {
	
	public static void inputThenPrintSumAndAverage()
	{
		Scanner sc = new Scanner(System.in);
		double sum = 0;
		double avg = 0;
		int n = 0;
		while(true)
		{
			try {
				String s = sc.nextLine();
				int number = Integer.parseInt(s);
				sum+=number;
				n+=1;
			}catch(NumberFormatException nfe)
			{
				System.out.println("Entered a string");
				break;
			}
		}
		
		avg = Math.round(sum/n);
		System.out.println("Sum = "+(int)sum+"\nAvg = "+avg);
	}
	
	public static void main(String[] args) {
		inputThenPrintSumAndAverage();
	}
}
