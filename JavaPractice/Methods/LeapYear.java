package Methods;

import java.util.Scanner;

public class LeapYear {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int year=scanner.nextInt();
		boolean result = isLeapYear(year);
		System.out.println(result);
		scanner.close();
	}
	public static boolean isLeapYear(int year) {
		boolean res = false;
		if ((year < 1) || (year > 9999)) {
			res = false;
		}
		else {
			if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
				res = true;
			}
			else {
				res = false;
			}
		}
		return res;
	}
}
