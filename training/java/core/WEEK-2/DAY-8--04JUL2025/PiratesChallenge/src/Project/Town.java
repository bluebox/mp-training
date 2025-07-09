package Project;

import java.util.*;

public record Town(
    String name,
    String island,
    int level,
    List<Loot> loot,
    List<Feature> features,
    List<Combatant> opponents
) {
    public Town {
        // Compact constructor: random loot/features/opponents
        Collections.shuffle(Arrays.asList(Loot.values()));
        Collections.shuffle(Arrays.asList(Feature.values()));

        loot = new ArrayList<>(Arrays.asList(Loot.values()).subList(0, 2));
        features = new ArrayList<>(Arrays.asList(Feature.values()).subList(0, 2));
        opponents = new ArrayList<>();
        opponents.add(new Islander("Tiki", "Spear"));
        opponents.add(new Soldier("Guard", "Gun"));
    }

    public Town(String name, String island) {
        this(name, island, 1, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
    }
}

