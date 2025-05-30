package methods;

import java.util.Scanner;

public class LeapYearChecker {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Year:");
		System.out.println(isLeapYear(sc.nextInt()));
       
    }

	public static boolean isLeapYear(int year) {
        if (year < 1 || year > 9999) {
            return false;
        }
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

}