package corejava.july3_AdventureGame;

import java.util.Scanner;

public class AdventureGame {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
        GameMap gameMap = new GameMap();
        String current = "road";

        while (true) {
            Location location = gameMap.getLocation(current);
            System.out.println("You are " + location.getDescription());
            System.out.print("You can go: ");
            for (String d : location.getNextPlaces().keySet())
            	System.out.print(d + " ");
            System.out.println();
            System.out.println("Enter direction (N, S, E, W) or Q to quit:");
            String input = scanner.nextLine().toUpperCase();
            
            
            
            if(input.equals("N") || input.equals("S") || input.equals("E") || input.equals("W") ) {
            	if (location.getNextPlaces().containsKey(input)) {
                    current = location.getNextPlaces().get(input);
                } else {
                    System.out.println("You can't go that way.");
                }
            }
            else if (input.equals("Q")) {
                System.out.println("Thanks for playing!");
                break;
            }
            else {
            	System.out.println("You entered an invalid direction. Please try again");
            	break;
            }
        }
        scanner.close();
    }
}
