package com.prana;

import java.util.HashMap;
import java.util.Map;

public class Location {
    private final String name;
    private final String description;
    private final Map<String, String> exits;

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        this.exits = new HashMap<>();
    }

    public void addExit(String direction, String destination) {
        exits.put(direction.toUpperCase(), destination.toLowerCase());
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, String> getExits() {
        return exits;
    }

    public void showExits() {
        System.out.print("Exits: ");
        for (String dir : exits.keySet()) {
            System.out.print(dir + " ");
        }
        System.out.println();
    }
   
}
