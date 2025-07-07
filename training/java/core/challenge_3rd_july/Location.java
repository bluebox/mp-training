package challenge_3rd_july;

import java.util.HashMap;
import java.util.Map;

public class Location {
    private String description;
    private Map<String, String> nextPlaces;

    public Location(String description) {
        this.description = description;
        this.nextPlaces = new HashMap<>();
    }

    public void addDirection(String direction, String locationKey) {
        nextPlaces.put(direction, locationKey);
    }

    public String getDescription() {
        return description;
    }

    public Map<String, String> getNextPlaces() {
        return nextPlaces;
    }
}

