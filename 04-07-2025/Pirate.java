package com.prana;

public class Pirate {
    private String name;
    private int health = 100;
    private int score = 0;

    public Pirate(String name) {
        this.name = name;
    }

    public void applyFeature(Feature f) {
        health += f.getHealthEffect();
        System.out.println("Feature encountered: " + f + ", Health now: " + health);
    }

    public void collectLoot(Loot l) {
        score += l.getScore();
        System.out.println("Found loot: " + l + ", Score now: " + score);
    }

    public void takeDamage(int amount) {
        health -= amount;
        System.out.println(name + " takes " + amount + " damage. Health now: " + health);
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getScore() {
        return score;
    }
}
