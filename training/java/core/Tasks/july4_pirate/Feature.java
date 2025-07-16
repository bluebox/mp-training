package corejava.july4_pirate;

public enum Feature {
    ALLIGATOR("Alligator", -50),
    SNAKE_PIT("Snake Pit", -20),
    FRESHWATER("Freshwater", +25),
    PINEAPPLE("Pineapple", +10);

    private final String name;
    private final int healthValue;

    Feature(String name, int healthValue) {
        this.name = name;
        this.healthValue = healthValue;
    }

    public String getName() { 
    	return name; 
    }

    public int getHealthValue() { 
    	return healthValue;
    }
}
