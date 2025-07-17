package july4_pirate;

public final class Islander extends Combatant {
    public Islander(String name, String weapon, int health, int attackPower) {
        super(name, weapon, health, attackPower);
    }

    @Override
    public void attack(Combatant opponent) {
        System.out.println(name + " attacks with " + weapon);
        opponent.takeDamage(attackPower);
    }
}
