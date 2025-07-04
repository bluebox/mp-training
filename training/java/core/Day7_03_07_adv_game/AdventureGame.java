package Day7_03_07_adv_game;

import java.util.*;

public class AdventureGame {

    private Map<String, String> descriptions = new HashMap<>();
    private Map<String, Map<String, String>> paths = new HashMap<>();
    private String currentLocation = "road";

    public AdventureGame() {
        loadLocations();
    }

    private void loadLocations() {
        descriptions.put("road", "at the end of the road");
        descriptions.put("hill", "on top of hill with a view in all directions");
        descriptions.put("well house", "inside a well house for a small spring");
        descriptions.put("valley", "in a forest valley beside a tumbling stream");
        descriptions.put("forest", "at the edge of a thick dark forest");
        descriptions.put("lake", "by an alpine lake surrounded by wildflowers");
        descriptions.put("stream", "near a stream with a rocky bed");

        Map<String, String> roadPaths = new HashMap<>();
        roadPaths.put("W", "hill");
        roadPaths.put("E", "well house");
        roadPaths.put("S", "valley");
        roadPaths.put("N", "forest");
        paths.put("road", roadPaths);

        Map<String, String> hillPaths = new HashMap<>();
        hillPaths.put("N", "forest");
        hillPaths.put("E", "road");
        paths.put("hill", hillPaths);

        Map<String, String> wellPaths = new HashMap<>();
        wellPaths.put("W", "road");
        wellPaths.put("N", "lake");
        wellPaths.put("S", "stream");
        paths.put("well house", wellPaths);

        Map<String, String> valleyPaths = new HashMap<>();
        valleyPaths.put("N", "road");
        valleyPaths.put("W", "hill");
        valleyPaths.put("E", "stream");
        paths.put("valley", valleyPaths);

        Map<String, String> forestPaths = new HashMap<>();
        forestPaths.put("S", "road");
        forestPaths.put("E", "lake");
        paths.put("forest", forestPaths);

        Map<String, String> lakePaths = new HashMap<>();
        lakePaths.put("W", "forest");
        lakePaths.put("S", "well house");
        paths.put("lake", lakePaths);

        Map<String, String> streamPaths = new HashMap<>();
        streamPaths.put("W", "valley");
        streamPaths.put("N", "well house");
        paths.put("stream", streamPaths);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        while (!quit) {
            System.out.println("You are " + descriptions.get(currentLocation));
            System.out.println("Where do you want to go? Options: " + paths.get(currentLocation).keySet());
            System.out.print("Enter direction (N/S/E/W) or Q to quit: ");
            String input = scanner.nextLine().toUpperCase();

            if (input.equals("Q")) {
                quit = true;
            } else {
                Map<String, String> moves = paths.get(currentLocation);
                if (moves.containsKey(input)) {
                    currentLocation = moves.get(input);
                } else {
                    System.out.println("You can't go that way!");
                }
            }
        }

        System.out.println("Game over. Bye!");
    }

    public static void main(String[] args) {
        AdventureGame game = new AdventureGame();
        game.start();
    }
}

