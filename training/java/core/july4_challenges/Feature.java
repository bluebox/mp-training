package july4_pirate;

public enum Feature {
    ALLIGATOR("Alligator", -30),
    SNAKE_PIT("Snake Pit", -20),
    FRESHWATER_SPRING("Freshwater Spring", +25),
    PINEAPPLE("Pineapple", +10),
    QUICKSAND("Quicksand", -15);

    private final String name;
    private final int healthEffect;

    Feature(String name, int healthEffect) {
        this.name = name;
        this.healthEffect = healthEffect;
    }

    public String getName() { return name; }

    public int getHealthEffect() { return healthEffect; }
}
