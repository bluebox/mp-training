package Collections;

import java.util.HashMap;
import java.util.Scanner;

public class AdventureGame {
    private static HashMap<String, Location> locations = new HashMap<>();

    public static void main(String[] args) {
        initLocations();
        playGame();
    }

    private static void initLocations() {
        HashMap<String, String> roadExits = new HashMap<>();
        roadExits.put("N", "forest");
        roadExits.put("S", "valley");
        roadExits.put("E", "well house");
        roadExits.put("W", "hill");
        locations.put("road", new Location("on a road", roadExits));

        HashMap<String, String> forestExits = new HashMap<>();
        forestExits.put("S", "road");
        forestExits.put("W", "lake");
        locations.put("forest", new Location("in a dark forest", forestExits));

        HashMap<String, String> valleyExits = new HashMap<>();
        valleyExits.put("N", "road");
        valleyExits.put("W", "stream");
        locations.put("valley", new Location("in a sunny valley", valleyExits));

        HashMap<String, String> wellHouseExits = new HashMap<>();
        wellHouseExits.put("W", "road");
        wellHouseExits.put("N", "lake");
        wellHouseExits.put("S", "stream");
        locations.put("well house", new Location("inside a well house for a small spring", wellHouseExits));

        HashMap<String, String> hillExits = new HashMap<>();
        hillExits.put("E", "road");
        hillExits.put("N", "lake");
        locations.put("hill", new Location("on top of a hill", hillExits));

        HashMap<String, String> lakeExits = new HashMap<>();
        lakeExits.put("S", "well house");
        lakeExits.put("E", "forest");
        lakeExits.put("W", "hill");
        locations.put("lake", new Location("by a beautiful lake", lakeExits));

        HashMap<String, String> streamExits = new HashMap<>();
        streamExits.put("N", "well house");
        streamExits.put("E", "valley");
        locations.put("stream", new Location("near a stream with a rocky bed", streamExits));
    }

    private static void playGame() {
        Scanner scanner = new Scanner(System.in);
        String currentLocation = "road";

        while (true) {
            Location loc = locations.get(currentLocation);
            System.out.println("*** You're " + loc.getDescription() + " ***");
            System.out.println("From here, you can see:");

            loc.getExits().forEach((direction, place) -> 
                System.out.println("- " + locations.get(place).getDescription() + " to the " + 
                    getDirectionName(direction) + " (" + direction + ")")
            );

            System.out.print("Select Your Compass Direction (Q to quit) >> ");
            String input = scanner.nextLine().toUpperCase();

            if (input.equals("Q")) {
                System.out.println("Thanks for playing!");
                break;
            }

            if (loc.getExits().containsKey(input)) {
                currentLocation = loc.getExits().get(input);
            } else {
                System.out.println("You can't go that way!");
            }
        }
        scanner.close();
    }

    private static String getDirectionName(String direction) {
        switch (direction) {
            case "N": return "North";
            case "S": return "South";
            case "E": return "East";
            case "W": return "West";
            default: return "";
        }
    }

    static class Location {
        private String description;
        private HashMap<String, String> exits;

        public Location(String description, HashMap<String, String> exits) {
            this.description = description;
            this.exits = exits;
        }

        public String getDescription() {
            return description;
        }

        public HashMap<String, String> getExits() {
            return exits;
        }
    }
}
