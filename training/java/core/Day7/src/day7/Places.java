package day7;

import java.util.HashMap;
import java.util.Map;

public class Places {
    private String name;
    private String description;
    public Map<String, String> directions = new HashMap<>();

    public Places(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addDirections(String direction, String placeName) {
        directions.put(direction.toUpperCase(), placeName);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, String> getDirections() {
        return directions;
    }

    @Override
    public String toString() {
        return name + " - " + description;
    }
}