package June26;

public class MinutesToYearsAndDays {
	public static void main(String[] args) {
		printYearsAndDays(525600);
		printYearsAndDays(1051200);
		printYearsAndDays(561600);
	}
	public static void printYearsAndDays(long minutes) {
		if(minutes < 0) System.out.println("Invalid Value");
		int yrtemp = 365*24*60;
		long year = minutes / yrtemp;
		int dtemp = 24*60;
		long day = (minutes % yrtemp) / dtemp;
		System.out.println(minutes + " min = " + year + " y and " + day + " d");
	}
}
