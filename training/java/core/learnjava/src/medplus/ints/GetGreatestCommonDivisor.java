package medplus.ints;

import java.util.Scanner;

public class GetGreatestCommonDivisor {
	public static int getGreatestCommonDivisor(int value1,int value2) {
		if (value1%value2 == 0) return value2;
		return getGreatestCommonDivisor(value2,value1%value2);
	}
	public static int isvalid(int value1,int value2) {
		if ((value1 <10) || (value2 < 10)) return -1;
		return getGreatestCommonDivisor(value1,value2);
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter value1 : ");
		int value1 = scanner.nextInt();
		System.out.print("\nEnter value2 : ");
		int value2 = scanner.nextInt();
		if (isvalid(value1,value2) == -1) System.out.print("\nInvalid Input");
		else System.out.print("greatest common divisor : "+isvalid(value1,value2));
		scanner.close();
	}
	
}
