package corejava.june26_methods;

import java.util.Scanner;

public class MethodLength {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter a year");
		int year= sc.nextInt();
		System.out.println("Enter a month in numberto get no of days in it");
		int month=sc.nextInt();
		int daysInMonth=getDaysInMonth(month,year);
		System.out.print(daysInMonth);
	}
	
	//leap year check
	public static boolean isLeapYear(int year) {
		if(year>1 && year<=9999) {
			if( (year%4==0 && year%100!=0) || year%400==0) {
				return true;
			}
			else
				return false;
		}
		else 
			return false;
	}
	
	//to get number of days in a month
	public static int getDaysInMonth(int month,int year) {
		if(month<1 || month>12 || year<1 || year>9999)
			return -1;
		switch(month) {
			case 1: case 3: case 5: case 7: case 9: case 11:{
				return 31;
			}
			case 4: case 6: case 8: case 10: case 12:{
				return 30;
			}
			case 2:{
				if(isLeapYear(year)) {
					return 29;
				}
				return 28;
			}
			default:{
				return -1;	
			}
		}	
	}
}
