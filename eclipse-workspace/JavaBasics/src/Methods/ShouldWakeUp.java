package Methods;

import java.util.Scanner;

public class ShouldWakeUp {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		boolean isBarking = scanner.nextBoolean();
		int hoursOfTheDay = scanner.nextInt();
		boolean result = shouldWakeUp(isBarking, hoursOfTheDay);
		System.out.println(result);
		scanner.close();
	}
	public static boolean shouldWakeUp(boolean isBarking, int hoursOfTheDay) {
		boolean res = false;
		if ((!isBarking) || (hoursOfTheDay < 0 || hoursOfTheDay > 23)) {
			res = false;
		}
		else if ((isBarking) && (hoursOfTheDay < 8 || hoursOfTheDay > 22)) {
			res = true;
		}
		return res;
	}
}
