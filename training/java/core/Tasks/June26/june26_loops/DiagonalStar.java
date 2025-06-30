package corejava.june26_loops;

import java.util.Scanner;

public class DiagonalStar {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the size of the square(side length)");
		int number=sc.nextInt();
		printSquareStar(number);
		sc.close();
	}
	public static void printSquareStar(int number) {
		if(number<5) {
			System.out.println("Invalid Input!!!");
		}
		else {
			for(int i=0;i<number;i++) {
				for(int j=0;j<number;j++) {
					if(i==0 || i==number-1 || j==0 || j==number-1 ||i==j ||i+j==number-1) {
						System.out.print("*");
					}
					else {
						System.out.print(" ");
					}
				}
				System.out.println();
			}
		}
	}
}
