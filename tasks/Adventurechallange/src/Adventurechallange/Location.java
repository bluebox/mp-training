package Adventurechallange;
import java.util.HashMap;
import java.util.Map;

public class Location {
    private final String key;
    private final String description;
    private final Map<String, String> exits;

    public Location(String key, String description) {
        this.key = key;
        this.description = description;
        this.exits = new HashMap<>();
    }

    public void addExit(String direction, String destinationKey) {
        exits.put(direction, destinationKey);
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, String> getExits() {
        return exits;
    }
}
