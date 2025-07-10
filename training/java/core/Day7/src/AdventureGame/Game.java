package AdventureGame;

import java.util.*;

public class Game {

    private enum Compass {
        N, S, E, W;

        public String fullName() {
            switch (this) {
                case N: return "North";
                case S: return "South";
                case E: return "East";
                case W: return "West";
                default: return "";
            }
        }
    }

    private record Location(String description, Map<Compass, String> exits) {}

    private Map<String, Location> map = new HashMap<>();
    private String currentLocation;

    public Game(String data) {
        loadMap(data);
    }

    private void loadMap(String data) {
        String[] lines = data.split("\\R");

        for (String line : lines) {
            String[] parts = line.split(",", 3);
            if (parts.length < 3) continue;

            String name = parts[0].trim();
            String description = parts[1].trim();
            String directionsText = parts[2].trim();

            Map<Compass, String> exits = new HashMap<>();
            String[] directions = directionsText.split(",");

            for (String d : directions) {
                String[] dirParts = d.split(":");
                if (dirParts.length == 2) {
                    try {
                        Compass dir = Compass.valueOf(dirParts[0].trim());
                        String destination = dirParts[1].trim();
                        exits.put(dir, destination);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid direction in map: " + d);
                    }
                }
            }

            map.put(name, new Location(description, exits));
        }
    }

    public void play(String start) {
        if (!map.containsKey(start)) {
            System.out.println("Invalid start location: " + start);
            return;
        }

        currentLocation = start;
        showLocation();
    }

    private void showLocation() {
        Location loc = map.get(currentLocation);

        if (loc == null) {
            System.out.println("Error: Current location is invalid.");
            return;
        }

        System.out.println();
        System.out.println("You are " + loc.description);

        // Gather available directions as [N, S, E, W]
        List<String> directionOptions = new ArrayList<>();
        for (Compass c : loc.exits.keySet()) {
            directionOptions.add(c.toString());
        }

        System.out.println("Where do you want to go? Options: " + directionOptions);
        System.out.print("Enter direction (N/S/E/W) or Q to quit: ");
    }

    public void move(String direction) {
        Compass dir;
        try {
            dir = Compass.valueOf(direction);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid direction! Use N, S, E, or W.");
            showLocation();
            return;
        }

        Location current = map.get(currentLocation);
        if (current != null && current.exits.containsKey(dir)) {
            String nextLocation = current.exits.get(dir);
            if (map.containsKey(nextLocation)) {
                currentLocation = nextLocation;
                showLocation();
            } else {
                System.out.println("That location doesn't exist in the map.");
                showLocation();
            }
        } else {
            System.out.println("You can't go that way!");
            showLocation();
        }
    }
}