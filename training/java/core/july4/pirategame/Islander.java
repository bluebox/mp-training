package dev.tulasidhar.july4.pirategame;

public final class Islander extends Combatant {

    public Islander(String name, Weapon weapon) {
        super(name);
        setCurrentWeapon(weapon);
    }
}
