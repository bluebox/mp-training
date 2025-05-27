package medplus.ints;

import java.util.Scanner;

public class IsPerfectNumber {
	public static boolean isPerfectNumber(int value) {
		if (value < 1) return false;
		else {
			int sum=0;
			for (int i =1;i<value;i++) {
				if (value%i==0) sum+=i;
			}
			if (sum == value) return true;
			return false;
		}
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a value : ");
		int value = scanner.nextInt();
		System.out.print(value+" is a perfect number : "+isPerfectNumber(value));
		scanner.close();
	}

}
