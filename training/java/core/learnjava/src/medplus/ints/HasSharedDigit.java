package medplus.ints;

import java.util.Scanner;

public class HasSharedDigit {
	public static boolean hasSharedDigit(int value1,int value2){
		if (value1<10 || value1 >99 || value2 <10 || value2 >99) return false;
		else {
			if ((value1%10 == value2%10) || (value1%10 == value2/10) || (value1/10 == value2%10) || (value1/10 == value2/10)) return true;
			return false;
		}
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter value1 : ");
		int value1 = scanner.nextInt();
		System.out.print("Enter value2 : ");
		int value2 = scanner.nextInt();
		System.out.println(hasSharedDigit(value1,value2));
		scanner.close();
	}
}
