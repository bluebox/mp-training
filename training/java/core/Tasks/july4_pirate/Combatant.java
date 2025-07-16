package corejava.july4_pirate;

public sealed abstract class Combatant permits Pirate, Islander, Soldier, ReactiveSoldier {
    protected String name, weapon;
    protected int health, attackPower;

    public Combatant(String name, String weapon, int health, int attackPower) {
        this.name = name; 
        this.weapon = weapon;
        this.health = health; 
        this.attackPower = attackPower;
    }

    public String getName() { 
    	return name; 
    }
    public int getHealth() {
    	return health; 
    }

    public void takeDamage(int dmg) {
        health -= dmg;
        if (health < 0) 
        	health = 0;
    }

    public boolean isAlive() { 
    	return health > 0;
    }

    public abstract void attack(Combatant opponent);
}
