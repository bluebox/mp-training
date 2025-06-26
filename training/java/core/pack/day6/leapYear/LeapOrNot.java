package day6.leapYear;

public class LeapOrNot {
public boolean isLeapYear(int year) {
	
	boolean var=(year%4==0)?((year%100==0)?((year%400==0)?(true):(false)):(true)):(false);
	
	return var;
}
}
