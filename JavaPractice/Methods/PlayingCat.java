package Methods;

import java.util.Scanner;

public class PlayingCat {
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
			return ((temperature >= 25) && (temperature <= 45));
		}
		else {
			return ((temperature >= 25) && (temperature <= 35));
		}
	}
}
