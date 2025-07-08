package GameChallenge;
import java.util.HashMap;
import java.util.Map;

public class GameMap {
    private Map<String, Location> locations = new HashMap<>();

    public GameMap() {
        Location road = new Location("You are standing on a road in the center of the map.");
        road.addExit("N", "forest");
        road.addExit("S", "valley");
        road.addExit("E", "well house");
        road.addExit("W", "hill");

        Location forest = new Location("You are in a forest.");
        forest.addExit("S", "road");

        Location valley = new Location("You are in a quiet valley.");
        valley.addExit("N", "road");

        Location wellHouse = new Location("You are inside a well house.");
        wellHouse.addExit("W", "road");
        wellHouse.addExit("N", "lake");
        wellHouse.addExit("S", "stream");

        Location lake = new Location("You are at the edge of a lake.");
        lake.addExit("S", "well house");

        Location stream = new Location("You are near a stream with a rocky bed.");
        stream.addExit("N", "well house");
        stream.addExit("W", "valley");

        Location hill = new Location("You are on a hill. The view is breathtaking.");
        hill.addExit("E", "road");

        locations.put("road", road);
        locations.put("forest", forest);
        locations.put("valley", valley);
        locations.put("well house", wellHouse);
        locations.put("lake", lake);
        locations.put("stream", stream);
        locations.put("hill", hill);
    }

    public Location getLocation(String name) {
        return locations.get(name);
    }

    public boolean containsLocation(String name) {
        return locations.containsKey(name);
    }
}
