package com.prana;

public final class Soldier extends Combatant {

    public Soldier(String name) {
        super(name, "Rifle");
    }

    public void patrol() {
        System.out.println(name + " is patrolling with " + weapon + "!");
    }
}
