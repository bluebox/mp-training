package July4.Pirate;

public enum Loot {
	
    GOLD_COINS("Gold Coins", 50),
    PEARL_NECKLACE("Pearl Necklace", 75),
    EMERALD_RING("Emerald Ring", 100),
    TREASURE_MAP("Treasure Map", 40),
    RUBY("Ruby", 90);

    private final String name;
    private final int value;

    Loot(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() { 
    	return name;
    }

    public int getValue() { 
    	return value;
    }
}
