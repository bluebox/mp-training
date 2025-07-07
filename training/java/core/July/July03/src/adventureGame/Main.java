package adventureGame;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		
		 Scanner scanner = new Scanner(System.in);
		 Map<String, Locations> map = new HashMap<>();
		 
		 Locations road = new Locations("Standing on a Road");
		 Locations forest = new Locations("You are in the forest");
		 Locations lake = new Locations("You are in the forest");
		 Locations wellHouse= new Locations("You are in the forest");
		 Locations stream = new Locations("You are in the forest");
		 Locations valley = new Locations("You are in the forest");
		 Locations hill = new Locations("You are in the forest");
		 
		 map.put("road",road);
		 road.addNextPlaces("N", "Forest");
		 road.addNextPlaces("S", "Valley");
		 road.addNextPlaces("E", "WellHouse");
		 road.addNextPlaces("W","Hill");
		 
		 map.put("forest", forest);
		 forest.addNextPlaces("S", "Road");
		 forest.addNextPlaces("E", "Lake");
		 
		 map.put("lake",lake);
		 lake.addNextPlaces("W","Forest");
		 lake.addNextPlaces("S", "WellHouse");
		 
		 map.put("wellhouse",wellHouse);
		 wellHouse.addNextPlaces("N", "Lake");
		 wellHouse.addNextPlaces("S", "Stream");
		 wellHouse.addNextPlaces("W", "Road");
 
		 map.put("stream", stream);
		 stream.addNextPlaces("N", "WellHouse");
		 stream.addNextPlaces("W", "Valley");
		 
		 map.put("valley",valley);
		 valley.addNextPlaces("N", "Road");
		 valley.addNextPlaces("W", "Hill");
		 valley.addNextPlaces("E", "Stream");
		 
		 map.put("hill",hill);
		 hill.addNextPlaces("E", "Valley");
		 hill.addNextPlaces("N", "Forest");
		 
		 String presentLocation = "road";
		 Map<String, String> vocabulary = new HashMap<String, String>();
	     vocabulary.put("QUIT", "Q");
	     vocabulary.put("NORTH", "N");
	     vocabulary.put("EAST", "E");
	     vocabulary.put("SOUTH", "S");
	     vocabulary.put("WEST", "W");
		 while(true) {
			 
			System.out.println(map.get(presentLocation.toLowerCase()).getDescription());
            
            Map<String, String> nextLocations = map.get(presentLocation.toLowerCase()).getNextPlaces();
            System.out.print("Available exits are ");
            for(String location: nextLocations.keySet()) {
                System.out.print(location + " : "+ nextLocations.get(location)+ " , ");
            }
            System.out.println("Q : Quit");

            String direction = scanner.nextLine().toUpperCase();
            if(direction.length() > 1){
                String[] words = direction.split(" ");
                for(String word : words){
                    if(vocabulary.containsKey(word)){
                        direction = vocabulary.get(word);
                        break;
                    }
                }
            }

            if(nextLocations.containsKey(direction)) {
                presentLocation = nextLocations.get(direction);
            } else {
                System.out.println("You cannot go in that direction");
                if(direction.charAt(0) == 'Q') {
                	break;
                }
            }
		 }
		 scanner.close();;
	}
}
