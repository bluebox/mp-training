package July3;

import java.util.Scanner;
import java.util.Map;

public class GameMain {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Game game = new Game();
		Map<String, Location> locations = game.getLocations();
		String currentLocationKey = "road";

		while (true) {
			Location current = locations.get(currentLocationKey);
			System.out.println("You are " + current.getDescription());
			System.out.println("Available  " + current.getExits().keySet());
			System.out.print("Enter direction (N, S, E, W or Q): ");
			String direction = sc.nextLine().toUpperCase();

			if (direction.equals("Q")) {
				System.out.println("Thank you ....");
				break;
			}

			if (current.getExits().containsKey(direction)) {
				currentLocationKey = current.getExits().get(direction);
			} else {
				System.out.println("can't go ");
			}
		}
	}
}
