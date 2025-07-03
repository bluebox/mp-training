package com.prana;

import java.util.*;

public class AdventureGame {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, Location> world = new HashMap<>();
    private static String currentLocation = "road";

    public static void main(String[] args) {
        setupWorld();

        System.out.println(" Welcome to the Adventure Game!");
        while (true) {
            Location location = world.get(currentLocation);
            System.out.println("\n You are " + location.getDescription());
            location.showExits();

            System.out.print("Where do you want to go (N, S, E, W or Q to quit)? ");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("Q")) {
                System.out.println(" Thanks for playing!");
                break;
            }

            if (location.getExits().containsKey(input)) {
                currentLocation = location.getExits().get(input);
            } else {
                System.out.println(" You can't go that way.");
            }
        }
    }

    private static void setupWorld() {
        Location road = new Location("road", "standing on a road in the center of the map");
        Location forest = new Location("forest", "in a dark, quiet forest");
        Location valley = new Location("valley", "in a deep valley surrounded by cliffs");
        Location wellHouse = new Location("well house", "inside a well house for a small spring");
        Location hill = new Location("hill", "on top of a windy hill");
        Location lake = new Location("lake", "at the edge of a beautiful lake");
        Location stream = new Location("stream", "near a stream with a rocky bed");

        road.addExit("N", "forest");
        road.addExit("S", "valley");
        road.addExit("E", "well house");
        road.addExit("W", "hill");

        forest.addExit("S", "road");
        forest.addExit("N", "lake");

        lake.addExit("S", "forest");

        valley.addExit("N", "road");
        valley.addExit("S", "stream");

        stream.addExit("N", "valley");

        wellHouse.addExit("W", "road");

        hill.addExit("E", "road");

        world.put("road", road);
        world.put("forest", forest);
        world.put("valley", valley);
        world.put("well house", wellHouse);
        world.put("hill", hill);
        world.put("lake", lake);
        world.put("stream", stream);
    }
}
