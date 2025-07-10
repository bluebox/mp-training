package July4.Pirate;

public final class ReactiveSoldier extends Combatant {
	
    public ReactiveSoldier(String name, String weapon, int health, int attackPower) {
        super(name, weapon, health, attackPower);
    }

    @Override
    public void attack(Combatant opponent) {
        System.out.println(name + " fires " + weapon + " at " + opponent.getName());
        opponent.takeDamage(attackPower);
    }

    public void retaliate(Pirate pirate) {
        System.out.println(name + " retaliates with " + weapon + "!");
        pirate.takeDamage(attackPower);
    }
}
