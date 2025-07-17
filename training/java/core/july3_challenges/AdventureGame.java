package july_3Challenges;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AdventureGame {
    public static void main(String[] args) {
   
        Location road = new Location("You are on a road, with a forest to the north, valley to the south.");
        Location forest = new Location("You are in a dark forest.");
        Location valley = new Location("You are in a sunny valley.");
        Location wellHouse = new Location("You are inside a well house for a small spring.");
        Location hill = new Location("You are on top of a hill with a great view.");

    
        road.addDirection("N", forest);
        road.addDirection("S", valley);
        road.addDirection("E", wellHouse);
        road.addDirection("W", hill);

        forest.addDirection("S", road);
        valley.addDirection("N", road);
        wellHouse.addDirection("W", road);
        hill.addDirection("E", road);

      
        Map<String, Location> gameMap = new HashMap<>();
        gameMap.put("road", road);
        gameMap.put("forest", forest);
        gameMap.put("valley", valley);
        gameMap.put("wellhouse", wellHouse);
        gameMap.put("hill", hill);

       
        Scanner scanner = new Scanner(System.in);
        Location currentLocation = road;

        System.out.println(" Welcome to the Adventure Game!");
        System.out.println("Type direction (N, S, E, W) to move, or 'Q' to quit.\n");

        while (true) {
            System.out.println(currentLocation.getDescription());
            currentLocation.showAvailableDirections();
            System.out.print("Enter direction (N/S/E/W): ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("Q")) {
                System.out.println(" Thanks for playing");
                break;
            }

            Location next = currentLocation.move(input);
            if (next != null) {
                currentLocation = next;
            } else {
                System.out.println(" You can't go that way!");
            }
            System.out.println();
        }

        scanner.close();
    }
}
