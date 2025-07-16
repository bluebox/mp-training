package corejava.july3_AdventureGame;

import java.util.HashMap;

public class GameMap {
    private HashMap<String, Location> locations = new HashMap<>();

    public GameMap() {
        Location road = new Location("standing on the road in the center of the map.");
        road.addNextPlace("N", "forest");
        road.addNextPlace("S", "valley");
        road.addNextPlace("E", "well_house");
        road.addNextPlace("W", "hill");

        Location forest = new Location("in a dense forest.");
        forest.addNextPlace("E", "lake");
        forest.addNextPlace("S", "road");

        Location wellHouse = new Location("inside a well house.");
        wellHouse.addNextPlace("N", "lake");
        wellHouse.addNextPlace("S", "stream");
        wellHouse.addNextPlace("W", "road");

        Location valley = new Location("near a valley.");
        valley.addNextPlace("E", "stream");
        valley.addNextPlace("N", "road");

        Location hill = new Location("on a hill.");
        hill.addNextPlace("E", "road");
        
        Location lake = new Location("by the lake.");
        lake.addNextPlace("W", "forest");
        lake.addNextPlace("S", "well_house");
        
        Location stream = new Location("near a stream.");
        stream.addNextPlace("N", "well_house");
        stream.addNextPlace("W", "valley");

        locations.put("road", road);
        locations.put("forest", forest);
        locations.put("well_house", wellHouse);
        locations.put("valley", valley);
        locations.put("hill", hill);
        locations.put("lake", lake);
        locations.put("stream", stream);
    }

    public Location getLocation(String key) {
        return locations.get(key);
    }
}

