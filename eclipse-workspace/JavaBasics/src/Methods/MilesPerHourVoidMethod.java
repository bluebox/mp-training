package Methods;

import java.util.*;

public class MilesPerHourVoidMethod {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		double kilometerPerHour = scanner.nextDouble();
		milesPerHour(kilometerPerHour);
		scanner.close();
	}
	public static void milesPerHour(double kilometerPerHour) {
		if (kilometerPerHour <= 0.0d) {
			System.out.println("-1");
			return;
		}
		long milePerHour = (long)Math.round(kilometerPerHour * 0.621371);
		System.out.println(kilometerPerHour+"kmph is equal to "+milePerHour+"mlph");
	}
}
