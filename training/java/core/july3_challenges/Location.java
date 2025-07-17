package july_3Challenges;

import java.util.HashMap;
import java.util.Map;

public class Location {
    private final String description;
    private final Map<String, Location> nextPlaces;

    public Location(String description) {
        this.description = description;
        this.nextPlaces = new HashMap<>();
    }

    public String getDescription() {
        return description;
    }

    public void addDirection(String direction, Location location) {
        nextPlaces.put(direction.toUpperCase(), location);
    }

    public Location move(String direction) {
        return nextPlaces.get(direction.toUpperCase());
    }

    public void showAvailableDirections() {
        System.out.print("Available directions: ");
        for (String dir : nextPlaces.keySet()) {
            System.out.print(dir + " ");
        }
        System.out.println();
    }
}
