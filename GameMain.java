package GameChallenge;
import java.util.Map;
import java.util.Scanner;

public class GameMain {
    public static void main(String[] args) {
        GameMap gameMap = new GameMap();
        Scanner scanner = new Scanner(System.in);

        String currentLocation = "road";
        while (true) {
            Location location = gameMap.getLocation(currentLocation);
            System.out.println("\nLocation: " + location.getDescription());

            Map<String, String> exits = location.getExits();
            System.out.println("Available directions: " + exits.keySet());

            System.out.print("Enter direction (or Q to quit): ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("Q")) {
                System.out.println("Thanks for playing!");
                break;
            }

            if (exits.containsKey(input)) {
                currentLocation = exits.get(input);
            } else {
                System.out.println("You can't go that way!");
            }
        }

        scanner.close();
    }
}
