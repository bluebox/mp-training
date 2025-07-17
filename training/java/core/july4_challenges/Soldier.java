package july4_pirate;

public final class Soldier extends Combatant {
    public Soldier(String name, String weapon, int health, int attackPower) {
        super(name, weapon, health, attackPower);
    }

    @Override
    public void attack(Combatant opponent) {
        System.out.println(name + " fires " + weapon);
        opponent.takeDamage(attackPower);
    }
}
