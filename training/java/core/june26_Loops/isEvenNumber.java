package june26_Loops;

import java.util.Scanner;

public class isEvenNumber {

	public static boolean isEvenNumber(int num) {
		return num % 2 == 0 ? true : false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter number: ");
		int number=sc.nextInt();
		if(isEvenNumber(number))
		System.out.println("The Given Number "+number+" is a Even Number");
		else
			System.out.println("The Given Number "+number+" is a odd Number");
		int start=5;
		int end=20;
		int num=start;
		int ec=0,oc=0;
		System.out.println("While loop begins....\nthe first '5' even numbers from 5 to 20 are");
		while(num<=20) {
			if(isEvenNumber(num)) {
				ec+=1;
				if(ec<=5)
					System.out.println(num+" is a Even Number");}
			
			else
				oc+=1;
			num+=1;
		}
		System.out.println("Total number of Even numbers from 5 to 20 : "+ec);
		System.out.println("Total number of Odd numbers from 5 to 20 : "+oc);
		
		
		sc.close();
	}

}
