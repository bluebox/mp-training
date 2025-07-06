package Adventurechallange;

import java.util.HashMap;
import java.util.Map;

public class Game {
    private final Map<String, Location> locations;

    public Game() {
        locations = new HashMap<>();
        initializeLocations();
    }

    private void initializeLocations() {
        Location road = new Location("road", "standing on a road in the center of the map");
        Location forest = new Location("forest", "in a spooky forest with tall trees");
        Location valley = new Location("valley", "in a lush valley with flowers");
        Location wellHouse = new Location("well house", "inside a well house for a small spring");
        Location hill = new Location("hill", "on a hill with a wide view");
        Location stream = new Location("stream", "near a stream with a rocky bed");
        Location lake = new Location("lake", "at the edge of a quiet lake");

        locations.put(road.getKey(), road);
        locations.put(forest.getKey(), forest);
        locations.put(valley.getKey(), valley);
        locations.put(wellHouse.getKey(), wellHouse);
        locations.put(hill.getKey(), hill);
        locations.put(stream.getKey(), stream);
        locations.put(lake.getKey(), lake);

        road.addExit("N", "forest");
        road.addExit("S", "valley");
        road.addExit("E", "well house");
        road.addExit("W", "hill");

        forest.addExit("S", "road");
        valley.addExit("N", "road");
        wellHouse.addExit("W", "road");
        hill.addExit("E", "road");

        stream.addExit("W", "valley");
        stream.addExit("N", "well house");
        lake.addExit("N", "stream");
    }

    public Map<String, Location> getLocations() {
        return locations;
    }
}
