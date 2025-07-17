package july4_pirate;

public non-sealed class Pirate extends Combatant {
    private int score;

    public Pirate(String name, String weapon, int health, int attackPower) {
        super(name, weapon, health, attackPower); 
        this.score = 0;
    }

    @Override
    public void attack(Combatant opponent) {
        System.out.println(name + " slashes with " + weapon);
        opponent.takeDamage(attackPower);
        if (opponent instanceof ReactiveSoldier rs && rs.isAlive()) {
            rs.retaliate(this);
        }
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
