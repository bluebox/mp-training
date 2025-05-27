package Methods;

import java.util.Scanner;

public class PlayingCat2 {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		boolean isSummer = scanner.nextBoolean();
		int temperature = scanner.nextInt();
		boolean result = isCatPlaying(isSummer,temperature);
		System.out.println(result);
		scanner.close();
	}

	public static boolean isCatPlaying(boolean isSummer, int temperature) {
		if (isSummer) {
			return isValid(temperature,25,45);
		}
		else {
			return isValid(temperature,25,35);
		}
	}

	public static boolean isValid(int temperature, int low, int high) {
		return ((temperature >= low) && (temperature <= high));
	}
}
