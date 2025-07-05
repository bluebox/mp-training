package adventure;

import java.util.HashMap;
import java.util.Map;

public class Location {
	 private final String description;
	    private final Map<String, String> nextPlaces;

	    public Location(String description) 
	    {
	        this.description = description;
	        this.nextPlaces = new HashMap<>();
	    }

	    public String getDescription() {
	        return description;
	    }

	    public Map<String, String> getNextPlaces() 
	    {
	        return new HashMap<>(nextPlaces); 
	    }

	    public void addExit(String direction, String locationKey)
	    {
	        nextPlaces.put(direction.toUpperCase(), locationKey);
	    }
}
