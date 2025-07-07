package dev.tulasidhar.july3.adventurechallenge;
import java.util.*;

public class AdventureGame {
    
    static HashMap<String, Location> gameBoard = new HashMap<>();
    static Scanner input = new Scanner(System.in);
    static String currentLocation = "road";
    
    public static void main(String[] args) {
        setupGame();
        playGame();
    }
    
    static void setupGame() {
        Location road = new Location("standing on a dirt road at the center of the map");
        road.nextPlaces.put("n", "forest");
        road.nextPlaces.put("s", "valley");
        road.nextPlaces.put("e", "well house");
        road.nextPlaces.put("w", "hill");
        gameBoard.put("road", road);
        
        Location forest = new Location("in a dense forest with tall trees");
        forest.nextPlaces.put("s", "road");
        forest.nextPlaces.put("e", "lake");
        gameBoard.put("forest", forest);
        
        Location valley = new Location("in a peaceful valley with rolling hills");
        valley.nextPlaces.put("n", "road");
        valley.nextPlaces.put("e", "stream");
        gameBoard.put("valley", valley);
        
        Location wellHouse = new Location("inside a well house for a small spring");
        wellHouse.nextPlaces.put("w", "road");
        wellHouse.nextPlaces.put("n", "lake");
        wellHouse.nextPlaces.put("s", "stream");
        gameBoard.put("well house", wellHouse);
        
        Location hill = new Location("on top of a rocky hill with a great view");
        hill.nextPlaces.put("e", "road");
        gameBoard.put("hill", hill);
        
        Location lake = new Location("beside a calm lake with clear water");
        lake.nextPlaces.put("w", "forest");
        lake.nextPlaces.put("s", "well house");
        gameBoard.put("lake", lake);
        
        Location stream = new Location("near a stream with a rocky bed");
        stream.nextPlaces.put("w", "valley");
        stream.nextPlaces.put("n", "well house");
        gameBoard.put("stream", stream);
    }
    
    static void playGame() {
        System.out.println("Welcome to the Adventure Game!");
        System.out.println("Type 'quit' to exit the game.");
        System.out.println();
        
        while (true) {
            Location current = gameBoard.get(currentLocation);
            System.out.println("You are " + current.description);
            System.out.print("Available directions: ");
            
            for (String direction : current.nextPlaces.keySet()) {
                System.out.print(direction + " ");
            }
            System.out.println();
            
            System.out.print("Where do you want to go? ");
            String command = input.nextLine().toLowerCase();
            
            if (command.equals("quit")) {
                System.out.println("Thanks for playing!");
                break;
            }
            
            if (current.nextPlaces.containsKey(command)) {
                currentLocation = current.nextPlaces.get(command);
                System.out.println();
            } else {
                System.out.println("You can't go that way!");
                System.out.println();
            }
        }
    }
}

class Location {
    String description;
    HashMap<String, String> nextPlaces;
    
    Location(String desc) {
        description = desc;
        nextPlaces = new HashMap<>();
    }
}