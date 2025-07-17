package july4_pirate;

import java.util.HashMap;
import java.util.Map;

public class SealedMain {
	public static void main(String[] args) {
		Pirate myplayer = new Pirate("anu", "knife", 100, 25);
	    Map<String, Object> gameData = new HashMap<>();
	    Town town = new Town("hyderabad", gameData);
	    for (Feature f : town.features()) {
	    	System.out.println("Encountered: " + f.getName());
            int healthEffect = f.getHealthEffect();
	        if (healthEffect > 0) {
	            myplayer.heal(healthEffect);
	        } else {
	        	myplayer.takeDamage(-healthEffect);
	        }
	    }
	    for (Loot l : town.loot()) {
	    	System.out.println("Found: " + l.getName() + " (+" + l.getValue() + " points)");
	        myplayer.addScore(l.getValue());
	    }
	    for (Combatant enemy : town.opponents()) {
	    	System.out.println("Fighting: " + enemy.getName());
	        myplayer.attack(enemy);
	        if (enemy.isAlive()) {
	        	enemy.attack(myplayer);
	        }
	    }
	    System.out.println("\nFinal Stats:");
	    System.out.println("Health: " + myplayer.getHealth());
	    System.out.println("Score: " + myplayer.getScore());
	    System.out.println("Game Data: " + gameData);
	}
}
