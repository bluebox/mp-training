package day8.diagonalstar;

import java.util.Scanner;

public class DiagonalStar {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		int t1;
		System.out.println("Enter the number & enter -1 to exit");
	try{
		while(true) {
			t1=sc.nextInt();
			if(t1==-1)
				break;
			
			StarDiagonals.printDiagonals(t1);
			
		}
	}
	catch(Exception e){
	System.out.println("Raised Exception try again with valid inputs");
	}
	}
}
