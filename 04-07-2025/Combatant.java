package com.prana;

public sealed abstract class Combatant permits Pirates, Islander, Soldier {

    protected String name;
    protected String weapon;
    protected int health = 100;
    protected int score = 0;
    
    public Combatant(String name, String weapon) {
        this.name = name;
        this.weapon = weapon;
    }

    public String getName() {
        return name;
    }

    public String getWeapon() {
        return weapon;
    }

    public int getHealth() {
        return health;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int value) {
        score += value;
    }
   
    public void takeDamage(int amount) {
        health -= amount;
        System.out.printf("%s takes %d damage. Health now: %d\n", name, amount, health);
    }

    public void heal(int amount) {
        health += amount;
        System.out.printf("%s heals %d points. Health now: %d\n", name, amount, health);
    }

}
