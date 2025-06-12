package Methods;

import java.util.*;

public class MilesPerHour {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		double kilometerPerHour = scanner.nextDouble();
		if (kilometerPerHour <= 0.0d) {
			System.out.println("-1");
			scanner.close();
			return;
		}
		long milePerHour = milesPerHour(kilometerPerHour);
		System.out.println(kilometerPerHour+"kmph is equal to "+milePerHour+"mlph");
		scanner.close();
	}
	public static long milesPerHour(double kilometersPerHour) {
		return (long)Math.round(kilometersPerHour * 0.621371);
	}
}
