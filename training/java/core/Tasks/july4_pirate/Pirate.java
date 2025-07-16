package corejava.july4_pirate;

public non-sealed class Pirate extends Combatant {
    private int score;

    public Pirate(String name, String weapon, int health, int attackPower) {
        super(name, weapon, health, attackPower); 
        this.score = 0;
    }

    @Override
    public void attack(Combatant opponent) {
    	if (opponent instanceof ReactiveSoldier reactiveSoldier && reactiveSoldier.isAlive()) {
        	reactiveSoldier.retaliate(this);
        }
        System.out.println(name + " attacks with " + weapon);
        opponent.takeDamage(attackPower);
    }

    public void heal(int amount) { 
    	health += amount; 
    }

    public void addScore(int points) { 
    	score += points; 
    }

    public int getScore() { 
    	return score; 
    }
}
