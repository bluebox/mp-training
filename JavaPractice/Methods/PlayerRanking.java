package Methods;

import java.util.*;

public class PlayerRanking {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		String playerName = scanner.next();
		int playerPosition = scanner.nextInt();
		displayHighScorePosition(playerName,playerPosition);
		scanner.close();
	}
	public static void displayHighScorePosition(String playerName, int playerPosition) {
		int result = 0;
		if (playerPosition >= 1000) {
			result = 1;
		}
		else if (playerPosition >= 500) {
			result = 2;
		}
		else if (playerPosition >= 100) {
			result = 3;
		}
		else {
			result = 4;
		}
		System.out.println(playerName+" ranking is "+result);
	}
}