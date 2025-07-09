package Project;

public final class Islander extends Combatant {
    public Islander(String name, String weapon) {
        super(name, weapon);
    }

    @Override
    public void useWeapon(Combatant opponent) {
        if (Math.random() < 0.3) {
            opponent.adjustHealth(-10);
            System.out.println(name + " hit " + opponent.getName() + "!");
        }
    }
}

