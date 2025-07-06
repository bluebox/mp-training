package HashMapAdavantureGame;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, AdvantureGame> locations = new HashMap<>();

        AdvantureGame stream = new AdvantureGame("near a stream with a rocky bed");
        stream.addNextPlace("W", "valley");
        stream.addNextPlace("N", "well house");
        locations.put("stream", stream);

        AdvantureGame wellHouse = new AdvantureGame("inside a well house for a small spring");
        wellHouse.addNextPlace("W", "road");
        wellHouse.addNextPlace("N", "lake");
        wellHouse.addNextPlace("S", "stream");
        locations.put("well house", wellHouse);

        for (String key : locations.keySet()) {
            AdvantureGame loc = locations.get(key);
            System.out.println("Location: " + key);
            System.out.println("Description: " + loc.getDescription());
            System.out.println("Next Places:");
            for (String dir : loc.getNextPlaces().keySet()) {
                System.out.println("  " + dir + " -> " + loc.getNextPlaces().get(dir));
            }
            System.out.println();
        }
    }
}

