package Project;

public enum Feature {
    ALLIGATOR(-30),
    FRESHWATER_SPRING(20),
    PINEAPPLE(5),
    TRAP(-15),
    HEALER(25);

    private final int healthImpact;

    Feature(int healthImpact) {
        this.healthImpact = healthImpact;
    }

    public int getHealthImpact() {
        return healthImpact;
    }
}

