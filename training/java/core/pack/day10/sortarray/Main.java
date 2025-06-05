package day10.sortarray;

import java.util.Scanner;

public class Main {
public static void main(String args[]) {
	SortArray sa=new SortArray();
	System.out.println("enter no.of integers in array");
	Scanner sc=new Scanner(System.in);
	int n=sc.nextInt();
	int a[]=sa.getIntegers(n);
	int b[]=sa.sortIntegers(a);
	sa.printIntegers(b);

	
}
}
