package day6.leapYear;

public class LeapOrNot2 {
	public boolean isLeapYear(int year) {
	boolean leap;
	if(year%4==0) {
		if(year%100==0) {
			if(year%400==0)
				leap=true;
			else {
				leap=false;
			}
		}
		else {
			leap=true;
		}
		
	}
	else {
		leap=false;
	}
	return leap;
}
}
