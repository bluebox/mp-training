package medplus.ints;

import java.util.Scanner;

public class HasSameLastDigit {
	
	public static boolean hasSameLastDigit(int value1 , int value2, int value3) {
		if (isValid(value1) && isValid(value2) && isValid(value3)) {
			if ((value1%10 == value2%10) || (value2%10 == value3%10) || (value1%10 == value3%10)) {
				return true;
			}else return false;
		}else {
			return false;
		}
	}
	public static boolean isValid(int value) {
		if (value >=10 && value <=1000) return true;
		return false;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter value1 : ");
		int value1 = scanner.nextInt();
		System.out.println("Enter value2 : ");
		int value2 = scanner.nextInt();
		System.out.println("Enter value3 : ");
		int value3 = scanner.nextInt();
		
		if (hasSameLastDigit(value1,value2,value3)) System.out.println("true");
		else System.out.println("False");
		
		scanner.close();
	}
	

}
