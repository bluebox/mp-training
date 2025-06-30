package corejava.june25_datatypes;

import java.util.Scanner;

public class PoundsToKilograms {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the no of pounds to convert into kilograms: ");
		int pounds=sc.nextInt();
		double kilograms=pounds*0.45359237d;
		System.out.print(pounds+" number of pounds is equals to "+kilograms+" kilograms");
		sc.close();
	}
}
