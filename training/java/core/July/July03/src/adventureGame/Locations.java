package adventureGame;

import java.util.HashMap;
import java.util.Map;

public class Locations {
	
	private final String description;
	private final Map<String, String> nextPlaces;
	
	public Locations(String description) {
		this.description = description;
		this.nextPlaces = new HashMap<>();
	}
	
	public String getDescription() {
		return description;
	}

	public Map<String, String> getNextPlaces() {
		return new HashMap<>(nextPlaces);
	}
	
	public void addNextPlaces(String direction, String locationDescription) {
		nextPlaces.put(direction, locationDescription);
	}
	
}
