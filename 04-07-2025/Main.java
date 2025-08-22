package com.prana;

public class Main {
    public static void main(String[] args) {
        Pirates pirate = new Pirates("Black Jack");
        Islander islander = new Islander("Maui");
        Soldier soldier = new Soldier("Captain Flint");

        System.out.println("--- Combatants created ---");
        System.out.println(pirate.getName() + " armed with " + pirate.getWeapon());
        System.out.println(islander.getName() + " armed with " + islander.getWeapon());
        System.out.println(soldier.getName() + " armed with " + soldier.getWeapon());

        System.out.println("\n--- Actions ---");
        pirate.takeDamage(20);
        islander.defendIsland();
        soldier.patrol();

        System.out.println("\n--- Pirate collects loot and encounters features ---");
        // pirate.collectLoot(Loot.GOLD_COINS);
        // pirate.applyFeature(Feature.ALLIGATOR);

        System.out.printf("\n%s's health: %d\n", pirate.getName(), pirate.getHealth());
        System.out.printf("%s's score: %d\n", pirate.getName(), pirate.getScore());
    }
}
