package GameChallenge;

import java.util.HashMap;
import java.util.Map;

public class Location {
    private String description;
    private Map<String, String> exits;

    public Location(String description) {
        this.description = description;
        this.exits = new HashMap<>();
    }

    public String getDescription() {
        return description;
    }

    public void addExit(String direction, String destination) {
        exits.put(direction.toUpperCase(), destination);
    }

    public Map<String, String> getExits() {
        return exits;
    }
}
