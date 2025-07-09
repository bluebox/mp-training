package day2;

public class LeapYear {

	public static void main(String[] args) {
		System.out.println(isLeapYear(-1600));
		System.out.println(isLeapYear(1600));
		System.out.println(isLeapYear(2017));
		System.out.println(isLeapYear(2020));
	}
	
	public static boolean isLeapYear(int year) {
		if (year<0 || year>9999) return false;
		if(year%400==0) return true;	
		if(year%100==0) return false;	
		if(year%4==0) return true;	
		return false;
	}
}
