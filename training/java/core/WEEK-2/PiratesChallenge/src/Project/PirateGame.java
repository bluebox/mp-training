package Project;

import java.util.List;

public class PirateGame {
    private Pirate player;
    private List<Town> towns;

    public PirateGame() {
        player = new Pirate("Jack", "Sword");
        towns = List.of(
            new Town("Tortuga", "Isla de Muerta"),
            new Town("Port Royal", "Jamaica"),
            new Town("Dead Man's Cove", "Unknown")
        );
    }

    public void play() {
        for (Town town : towns) {
            System.out.println("Visiting: " + town.name());
            for (Loot loot : town.loot()) {
                player.increaseScore(loot.getScore());
                System.out.println("Found loot: " + loot + " (+ " + loot.getScore() + ")");
            }

            for (Feature feature : town.features()) {
                player.adjustHealth(feature.getHealthImpact());
                System.out.println("Encountered feature: " + feature + " (health impact: " + feature.getHealthImpact() + ")");
            }

            for (Combatant opponent : town.opponents()) {
                player.useWeapon(opponent);
                opponent.useWeapon(player);
            }

            System.out.println("Health: " + player.getHealth() + ", Score: " + player.getScore());
        }
    }

    public static void main(String[] args) {
        new PirateGame().play();
    }
}

