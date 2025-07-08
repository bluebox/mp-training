package dev.tulasidhar.july4.pirategame;

public final class Soldier extends Combatant {

    public Soldier(String name, Weapon weapon) {
        super(name);
        setCurrentWeapon(weapon);
    }
}
