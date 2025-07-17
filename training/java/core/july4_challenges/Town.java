package july4_pirate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public record Town(
    String name,
    String island,
    int level,
    List<Loot> loot,
    List<Feature> features,
    List<Combatant> opponents
) {
    public Town {
        loot = Arrays.stream(Loot.values())
                .sorted(Comparator.comparingInt(e -> new Random().nextInt()))
                .limit(3)
                .collect(Collectors.toList());

        features = Arrays.stream(Feature.values())
                .sorted(Comparator.comparingInt(e -> new Random().nextInt()))
                .limit(2)
                .collect(Collectors.toList());

        opponents = List.of(
                new Islander("rohit", "gun", 50, 10),
                new ReactiveSoldier("sanju", "bomb", 70, 15)
        );
    }

    public Town(String name, Map<String, Object> gameData) {
        this(name, "Mystic Isle", 1, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        gameData.put("townVisited", name);
        gameData.put("discoveredAt", new Date());
    }
}
