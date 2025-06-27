package Day2_26_06;

public class SecondsMinuits_ch1 {
//	Seconds And Minutes Challenge Bonus:
//		In this part of the challenge, we'll add validation to the methods as a bonus:
//		For the first method,
//		the seconds parameter should be >= 0.
//		For the second method,
//		the minutes parameter should be >= 0.
//		and the seconds parameter should be >= 0 and <= 59.
//		If either method is passed an invalid value, print out some type of meaningful message to the user.
	public static void main(String args[]) {
		System.out.println(converter(68451));
		System.out.println(converter(548,52));
		System.out.println(converter(547,56,25));
	}
	public static String converter(int seconds) {
		if(seconds>=0) {
			int mins=seconds/60;
			return converter(mins,seconds%60);
		}
		return "Invalid data";
	}
	public static String converter(int mins,int seconds) {
		if(mins>=0 && seconds>=0) {
			int hrs=mins/60;
			return converter(hrs,mins%60,seconds);
		}
		return "Invalid data";
	}
	public static String converter(int hrs,int mins,int seconds) {
		if(hrs>=0 && mins>=0 && seconds>=0) {
			return "Total duration is :"+hrs+" hrs "+mins+" mins "+seconds+" seconds ";
			
		}
		return "Invalid data";
	}
}
