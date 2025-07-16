package corejava.july3_AdventureGame;

import java.util.HashMap;
import java.util.Map;

public class Location {
    private String description;
    private Map<String, String> nextPlaces = new HashMap<>();

    public Location(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void addNextPlace(String direction, String locationKey) {
        nextPlaces.put(direction, locationKey);
    }

    public Map<String, String> getNextPlaces() {
        return nextPlaces;
    }
}

