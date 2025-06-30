package corejava.june26_methods;

import java.util.Scanner;

public class SumAvgCalc {

	public static void main(String[] args) {
		inputThenPrintSumAndAverage();
	}
	public static void inputThenPrintSumAndAverage() {
		Scanner sc=new Scanner(System.in);
		int sum=0;
		double avg=0;
		int count=0;
		while(true) {
			try {
				System.out.println("Enter a number");
				int n=Integer.parseInt(sc.next());
				sum+=n;
				count++;
			}
			catch (Exception e) {
				if(count!=0) {
					avg=(double)sum/count;	
				}
				System.out.println("SUM = "+sum+", AVG = "+avg);
				break;
			}
		}
		sc.close();
	}
}
