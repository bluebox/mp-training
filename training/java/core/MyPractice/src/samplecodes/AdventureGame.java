package samplecodes;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AdventureGame {
    private Map<Integer, Zone> areaMap = new HashMap<>();
    private Map<String, String> directionMap = new HashMap<>();

    AdventureGame() {
        areaMap.put(0, new Zone(0, "You are sitting in front of a computer learning Java"));
        areaMap.put(1, new Zone(1, "You are standing at the end of a road before a small brick building"));
        areaMap.put(2, new Zone(2, "You are at the top of a hill"));
        areaMap.put(3, new Zone(3, "You are inside a building, a well house for a small spring"));
        areaMap.put(4, new Zone(4, "You are in a valley beside a stream"));
        areaMap.put(5, new Zone(5, "You are in the forest"));

        areaMap.get(1).addPath("W", 2);
        areaMap.get(1).addPath("E", 3);
        areaMap.get(1).addPath("S", 4);
        areaMap.get(1).addPath("N", 5);

        areaMap.get(2).addPath("N", 5);

        areaMap.get(3).addPath("W", 1);

        areaMap.get(4).addPath("N", 1);
        areaMap.get(4).addPath("W", 2);

        areaMap.get(5).addPath("S", 1);
        areaMap.get(5).addPath("W", 2);
    }

    public void startAdventure() {
        Scanner input = new Scanner(System.in);

        directionMap.put("QUIT", "Q");
        directionMap.put("NORTH", "N");
        directionMap.put("SOUTH", "S");
        directionMap.put("EAST", "E");
        directionMap.put("WEST", "W");

        int currentLocation = 1;

        while (true) {
            System.out.println(areaMap.get(currentLocation).getZoneDescription());
            if (currentLocation == 0) {
                break;
            }

            Map<String, Integer> availablePaths = areaMap.get(currentLocation).getAvailablePaths();
            System.out.print("Available directions are ");
            for (String dir : availablePaths.keySet()) {
                System.out.print(dir + ", ");
            }
            System.out.println();

            String[] commandWords = input.nextLine().toUpperCase().split(" ");
            String chosenDirection = "";
            for (String word : commandWords) {
                if (directionMap.containsKey(word)) {
                    chosenDirection = directionMap.get(word);
                    break;
                }
            }

            if (availablePaths.containsKey(chosenDirection)) {
                currentLocation = availablePaths.get(chosenDirection);
            } else {
                System.out.println("You cannot go in that direction");
            }
        }
    }
}

class Zone {
    private final int zoneID;
    private final String zoneDescription;
    private final Map<String, Integer> paths;

    public Zone(int zoneID, String zoneDescription) {
        this.zoneID = zoneID;
        this.zoneDescription = zoneDescription;
        this.paths = new HashMap<>();
        this.paths.put("Q", 0); // quit option
    }

    public void addPath(String direction, int destinationZoneID) {
        paths.put(direction, destinationZoneID);
    }

    public int getZoneID() {
        return zoneID;
    }

    public String getZoneDescription() {
        return zoneDescription;
    }

    public Map<String, Integer> getAvailablePaths() {
        return new HashMap<>(paths);
    }
}
