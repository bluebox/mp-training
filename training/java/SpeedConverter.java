public class SpeedConverter {
	public static void main(String[] args) {
		System.out.println(toMilesPerHour(10));
		printConversion(25.42);
		
	}
	public static long toMilesPerHour(double kilometerPerHour) {
		if (kilometerPerHour <0) {return -1;}
		return (int)((kilometerPerHour)*0.621371+0.5);
		
	}
	public static void printConversion(double kilometerPerHour) {
		System.out.print(kilometerPerHour+" km/h = "+toMilesPerHour(kilometerPerHour)+" ml/h");
	}

}

