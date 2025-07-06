package HashMapAdavantureGame;
import java.util.Map;
import java.util.HashMap;


public class AdvantureGame {
	private String description;
    private Map<String, String> nextPlaces;

    public AdvantureGame(String description) {
        this.description = description;
        this.nextPlaces = new HashMap<>();
    }

    public String getDescription() {
        return description;
    }

    public Map<String, String> getNextPlaces() {
        return nextPlaces;
    }

    public void addNextPlace(String direction, String place) {
        nextPlaces.put(direction, place);
    }
}
