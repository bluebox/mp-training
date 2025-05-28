package Methods;

import java.util.Scanner;

public class GetGCD {
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
		if (a < b) {
			return getGCD(b, a);
		}
		if (b == 0) return a;
		return getGCD(b,a%b);
	}
}
