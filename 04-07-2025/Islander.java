
package com.prana;

public final class Islander extends Combatant {

    public Islander(String name) {
        super(name, "Spear");
    }

    public void defendIsland() {
        System.out.println(name + " defends the island with " + weapon + "!");
    }
}
