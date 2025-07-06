package com.prana;

import java.util.Random;

public class Opponent {
    private String name;
    private int health;
    private String weapon;

    public Opponent(String name, int health, String weapon) {
        this.name = name;
        this.health = health;
        this.weapon = weapon;
    }
    public boolean useWeapon(Opponent target) {
        boolean hit = new Random().nextBoolean();
        if (hit) {
            int damage = new Random().nextInt(15) + 5;
            target.health -= damage;
            System.out.printf("%s hits %s with %s for %d damage!\n", name, target.name, weapon, damage);
        } else {
            System.out.printf("%s misses %s with %s.\n", name, target.name, weapon);
        }
        return hit;
    }
    
    public boolean useWeapon(Pirate pirate) {
        boolean hit = new Random().nextBoolean();
        if (hit) {
            int damage = new Random().nextInt(15) + 5;
            pirate.takeDamage(damage);
            System.out.printf("%s hits pirate %s with %s for %d damage!\n", name, pirate.getName(), weapon, damage);
        } else {
            System.out.printf("%s misses pirate %s with %s.\n", name, pirate.getName(), weapon);
        }
        return hit;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }
}
