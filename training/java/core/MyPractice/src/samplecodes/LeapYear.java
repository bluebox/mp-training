package samplecodes;

public class LeapYear {
	public static void main(String[] args) {
		System.out.println(isLeapYear(2002));
	}
	public static boolean isLeapYear(int n) {
		if(n%4==0 && n%100!=0) return true;
		if(n%100==0 && n%400==0) return true;
		return false;
	}
}
