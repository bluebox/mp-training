package Day2_26_06;

public class MinsToYearsAndDays {
	public static void main(String args[]) {
		System.out.println(converter(64565457));
		System.out.println(converter(8976451));
		System.out.println(converter(-96845));
	}
	public static String converter(int mins) {
		if(mins<0) {
			return "Invalid Input";
		}
		int days=mins/(24*60);
		int years=0;
		if(days>365) {
			years=days/365;
			days=days%365;
		}
		return years+" years "+days + " days ";
	}
}
