package july4_pirate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Pirate jack = new Pirate("Anu", "knife", 100, 25);
        Map<String, Object> gameData = new HashMap<>();
        Town town = new Town("hyderabad", gameData);

        for (Feature f : town.features()) {
            System.out.println("Encountered: " + f.getName());
            int healthEffect = f.getHealthEffect();
            if (healthEffect > 0) {
                jack.heal(healthEffect);
            } else {
                jack.takeDamage(-healthEffect);
            }
        }

        for (Loot l : town.loot()) {
            System.out.println("Found: " + l.getName() + " (+" + l.getValue() + " points)");
            jack.addScore(l.getValue());
        }

        for (Combatant enemy : town.opponents()) {
            System.out.println("Fighting: " + enemy.getName());
            jack.attack(enemy);
            if (enemy.isAlive()) {
                enemy.attack(jack);
            }
        }

        System.out.println("\nFinal Stats:");
        System.out.println("Health: " + jack.getHealth());
        System.out.println("Score: " + jack.getScore());
        System.out.println("Game Data: " + gameData);
    }
}
