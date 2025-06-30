package corejava.june26_UserInput;

import java.util.Scanner;

public class Sumof5ValidNumbers {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int count=1;
		int sum=0;
		System.out.println("Enter any 5 whole numbers to get it's sum");
		while(count<=5) {
			System.out.println("Enter number #"+count);
			int n=sc.nextInt();
			if(n>=0) {
				sum+=n;
				count++;
			}
			else {
				System.out.println("Invalid number entered!!");
			}
			
		}
		System.out.println("Sum of given 5 whole numbers is "+sum);
		sc.close();
	}

}
