package com.prana;

import java.util.*;

public record Town(String name, String island, int level, 
                   List<Loot> loot, List<Feature> features, List<Opponent> opponents) {

    public Town(String name, String island, int level) {
        this(name, island, level, randomLoot(), randomFeatures(), defaultOpponents());
    }

    private static List<Loot> randomLoot() {
        List<Loot> all = new ArrayList<>(List.of(Loot.values())); 
        Collections.shuffle(all);
        return all.subList(0, new Random().nextInt(all.size() - 1) + 1);
    }

    private static List<Feature> randomFeatures() {
        List<Feature> all = new ArrayList<>(List.of(Feature.values())); 
        Collections.shuffle(all);
        return all.subList(0, new Random().nextInt(all.size() - 1) + 1);
    }


    private static List<Opponent> defaultOpponents() {
        return List.of(
            new Opponent("Captain Redbeard", 100, "Cutlass"),
            new Opponent("One-Eyed Sally", 90, "Pistol")
        );
    }
    public Town(String name, String island, int level, List<Loot> loot, List<Feature> features, List<Opponent> opponents) {
        this.name = name;
        this.island = island;
        this.level = level;
        this.loot = loot;
        this.features = features;
        this.opponents = opponents;
    }
}
