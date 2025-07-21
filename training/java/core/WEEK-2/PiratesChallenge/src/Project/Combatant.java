package Project;

public sealed abstract class Combatant permits Pirate, Islander, Soldier {
    protected String name;
    protected String weapon;
    protected int health;
    protected int score;

    public Combatant(String name, String weapon) {
        this.name = name;
        this.weapon = weapon;
        this.health = 100;
        this.score = 0;
    }

    public void adjustHealth(int amount) {
        health += amount;
    }

    public void increaseScore(int points) {
        score += points;
    }

    public int getHealth() {
        return health;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public abstract void useWeapon(Combatant opponent);
}
