package Methods;

import java.util.Scanner;

public class GetGCD1 {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		int result = getGCD(a,b);
		if (result == -1) {
			System.out.println("Invalid value! One of the parameter holds value < 10");
			return;
		}
		System.out.println("GCD of "+a+", "+b+" is "+result);
	}
	public static int getGCD(int a,int b) {
		if ((a < 10) || (b < 10)) return -1;
		if (a < b) {
			return getGCD(b, a);
		}
		while (b != 0) {
			int temp = b;
			b = a%b;
			a = temp;
		}
		return a;
	}
}
