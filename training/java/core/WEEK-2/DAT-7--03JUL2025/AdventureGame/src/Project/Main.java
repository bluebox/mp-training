package Project;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Map<String, Location> gameMap = new HashMap<>();

        // Stream Location
        Location stream = new Location("near a stream with a rocky bed");
        stream.addExit("W", "valley");
        stream.addExit("N", "well house");

        // Well House Location
        Location wellHouse = new Location("inside a well house for a small spring");
        wellHouse.addExit("W", "road");
        wellHouse.addExit("N", "lake");
        wellHouse.addExit("S", "stream");

        // Valley
        Location valley = new Location("in a valley beside a stream");
        valley.addExit("N", "stream");

        // Road (Start location)
        Location road = new Location("standing on a road at the center of the map");
        road.addExit("N", "forest");
        road.addExit("S", "valley");
        road.addExit("E", "well house");
        road.addExit("W", "hill");

        // Forest
        Location forest = new Location("in a forest with tall trees");
        forest.addExit("S", "road");

        // Hill
        Location hill = new Location("on a hill with a clear view around");
        hill.addExit("E", "road");

        // Lake
        Location lake = new Location("beside a calm and clear lake");
        lake.addExit("S", "well house");

        // Add locations to the map
        gameMap.put("stream", stream);
        gameMap.put("well house", wellHouse);
        gameMap.put("valley", valley);
        gameMap.put("road", road);
        gameMap.put("forest", forest);
        gameMap.put("hill", hill);
        gameMap.put("lake", lake);

        // Start game
        Scanner scanner = new Scanner(System.in);
        String currentLocationKey = "road";

        while (true) {
            Location currentLocation = gameMap.get(currentLocationKey);
            System.out.println("\nYou are " + currentLocation.getDescription());
            System.out.println("Available directions: " + currentLocation.getNextPlaces().keySet());
            System.out.print("Enter direction (or type 'quit'): ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("QUIT")) {
                System.out.println("Thanks for playing!");
                break;
            }

            if (currentLocation.getNextPlaces().containsKey(input)) {
                currentLocationKey = currentLocation.getNextPlaces().get(input);
            } else {
                System.out.println("You can't go that way!");
            }
        }

        scanner.close();
    }
}

