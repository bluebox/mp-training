package javaPrograms;

import java.util.Scanner;
public class EvenDigitSum {
	
	public static int getEvenDigitSum(int n) {
		int sum1=0,sum2=0;
		if(n<0) {
			return -1;
		}
		int rem,counter=0;
		while(n>0) {
			rem=n%10;
			if(counter%2==0) {
				sum1+=rem;
			}
			else {
				sum2+=rem;
			}
			counter+=1;
			n/=10;
		}
		if(counter%2==0) {
			return sum1;
		}
		else {
			return sum2;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the number : ");
		int n=sc.nextInt();
		int ans=getEvenDigitSum(n);
		if(ans>0) {
			System.out.println("The even digits sum of "+n+" is : "+ans);
		}
		else {
			System.out.println("Inavalid input");
		}
		sc.close();
	}

}
