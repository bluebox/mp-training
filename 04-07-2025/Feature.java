package com.prana;

public enum Feature {
    ALLIGATOR(-50),
    FRESHWATER_SPRING(30),
    PIRANHAS(-40),
    PINEAPPLE(10),
    CURSED_IDOL(-70);

    private final int healthEffect;

    Feature(int healthEffect) {
        this.healthEffect = healthEffect;
    }

    public int getHealthEffect() {
        return healthEffect;
    }
}
