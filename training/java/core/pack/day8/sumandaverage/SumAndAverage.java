package day8.sumandaverage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SumAndAverage {
public static void main(String args[]) {
	Scanner sc=new Scanner(System.in);
	int sum=0,inp,count=0;
	long avg;
	while(true) {
		
		try {
			inp=sc.nextInt();
			sum+=inp;
			count++;
		}
		catch(InputMismatchException e) {
			if(count==0) {
				System.out.println("no integers entered");
				break;
			}
			avg=Math.round(sum/count*1.0);
			System.out.println("sum = "+sum +" average is "+avg);
			break;
			
		}
	}
}
}
