package July3;

import java.util.HashMap;
import java.util.Map;

public class Location {
    private String key;
    private String description;
    private Map<String, String> exits;

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

	public void setKey(String key) {
		this.key = key;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setExits(Map<String, String> exits) {
		this.exits = exits;
	}
    
}