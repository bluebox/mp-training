package Project;

public final class Pirate extends Combatant {
    public Pirate(String name, String weapon) {
        super(name, weapon);
    }

    @Override
    public void useWeapon(Combatant opponent) {
        if (Math.random() < 0.5) {  // 50% chance to hit
            opponent.adjustHealth(-15);
            System.out.println(name + " hit " + opponent.getName() + " with " + weapon);
        } else {
            System.out.println(name + " missed!");
        }
    }
}

