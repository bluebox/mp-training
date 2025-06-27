package com.tulasidhar.june26;


//problem : given year and month , find leap year or not and with respect to that 
//			return the days in that month

public class ChallengeMonthLengthAndLeapYear {
	public static void main(String[] args) {
		System.out.println(getDaysInMonth(1, 2020));
		System.out.println(getDaysInMonth(2, 2020));
		System.out.println(getDaysInMonth(2, 2018));
		System.out.println(getDaysInMonth(-1, 2020));
		System.out.println(getDaysInMonth(1, -2020));
	}
	
	public static boolean isLeapYear(int year) {
		if(year % 4 == 0)  {
			//if year is divisible by 4 check with 100
			if(year % 100 == 0){
				//if year is divisible by 4 and 100 check with 400
				if(year % 400 == 0) {
					return true;
				}
				return false;
			}
			//code reached here so year is only div by 4 so its a leap year
			return true;
		}
		
		//if not at all div by 4 then not leap year
		return false;
	}
	
	public static int getDaysInMonth(int month, int year) {
		if((month<1 || month>12) || (year<1 || year>9999)) {
			return -1;
		}
		boolean isLeapYear = isLeapYear(year);
		
		//hear switch cases donot need break because method is exited after return
		switch(month) {
			case(1):
				return 31;
			case(2):
				return isLeapYear ?  29 : 28;
			case(3):
				return 31;
			case(4):
				return 30;
			case(5):
				return 31;
			case(6):
				return 30;
			case(7):
				return 31;
			case(8):
				return 31;
			case(9):
				return 30;
			case(10):
				return 31;
			case(11):
				return 30;
			case(12):
				return 31;
			
		}
		return -1;
	}
}
