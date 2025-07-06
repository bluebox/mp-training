package com.prana;

public final class Pirates extends Combatant {

	    public Pirates(String name) {
	        super(name, "Cutlass");
	    }

	    public void collectLoot(Loot loot) {
	        int value = loot.getScore();
	        addScore(value);
	        System.out.printf("%s found loot: %s (+%d score). Total score: %d\n", name, loot, value, score);
	    }

	    public void applyFeature(Feature feature) {
	        int effect = feature.getHealthEffect();
	        if (effect >= 0) {
	            heal(effect);
	        } else {
	            takeDamage(-effect);
	        }
	        System.out.printf("%s experienced feature: %s\n", name, feature);
	    }
	}

