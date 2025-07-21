package Project;

public final class Soldier extends Combatant {
    public Soldier(String name, String weapon) {
        super(name, weapon);
    }

    @Override
    public void useWeapon(Combatant opponent) {
        if (Math.random() < 0.7) {
            opponent.adjustHealth(-20);
            System.out.println(name + " shot " + opponent.getName() + "!");
        }
    }
}

