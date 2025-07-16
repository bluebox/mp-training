package corejava.july4_pirate;

import java.util.HashMap;
import java.util.Map;

public class SealedMain {
	public static void main(String[] args) {
		Pirate Deepika = new Pirate("Deepika", "Gun", 100, 25);
	    Map<String, Object> gameData = new HashMap<>();
	    Town town = new Town("Vijayawada", gameData);
	    
	    for (Feature f : town.features()) {
	    	System.out.println("Encountered: " + f.getName()+"("+f.getHealthValue()+")");
            int healthEffect = f.getHealthValue();
	        if (healthEffect > 0) {
	        	Deepika.heal(healthEffect);
	        } else {
	        	Deepika.takeDamage(-healthEffect);
	        }
	    }
	    
	    for (Loot l : town.loot()) {
	    	System.out.println("Found: " + l.getName() + " (+" + l.getValue() + " points)");
	    	Deepika.addScore(l.getValue());
	    }
	    
	    for (Combatant enemy : town.opponents()) {
	    	System.out.println("Fighting: " + enemy.getName());
	    	Deepika.attack(enemy);
	        if (enemy.isAlive()) {
	        	enemy.attack(Deepika);
	        }
	    }
	    System.out.println("Final Status:");
	    System.out.println("Health: " + Deepika.getHealth());
	    System.out.println("Score: " + Deepika.getScore());
	    System.out.println("Game Data: " + gameData);
	}
}
