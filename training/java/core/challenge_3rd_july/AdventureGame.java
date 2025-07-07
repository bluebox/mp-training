package challenge_3rd_july;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AdventureGame {
    public static void main(String[] args) {
        Map<String, Location> locations = new HashMap<>();

        // Create "stream" location
        Location stream = new Location("near a stream with a rocky bed");
        stream.addDirection("W", "valley");
        stream.addDirection("N", "well house");
        locations.put("stream", stream);

        // Create "well house" location
        Location wellHouse = new Location("inside a well house for a small spring");
        wellHouse.addDirection("W", "road");
        wellHouse.addDirection("N", "lake");
        wellHouse.addDirection("S", "stream");
        locations.put("well house", wellHouse);

        // Example: print directions from stream
//        Location loc = locations.get("stream");
//        System.out.println("You're at: " + loc.getDescription());
//        System.out.println("You can go to: " + loc.getNextPlaces());
        
        System.out.println("where do you want to start from: stram/wellHouse");
        
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        Location loc=locations.get(s);
        System.out.println("You're at: " + loc.getDescription());
        System.out.println("You can go to: " + loc.getNextPlaces());
        
    }
}

