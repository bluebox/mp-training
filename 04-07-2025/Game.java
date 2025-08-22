package com.prana;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        List<Town> towns = new ArrayList<>(List.of(
                new Town("Port Skull", "Skull Island", 1),
                new Town("Deadman’s Harbor", "Snake Island", 2)
        ));

        Pirate pirate = new Pirate("Black Jack");
        int currentTownIndex = 0;

        Scanner scanner = new Scanner(System.in);

        while (currentTownIndex < towns.size()) {
            Town currentTown = towns.get(currentTownIndex);
            System.out.println("\n You are now in " + currentTown.name() + " on " + currentTown.island());
            System.out.println("Pirate Health: " + pirate.getHealth() + " | Score: " + pirate.getScore());

            System.out.println("""
                    Choose an action:
                    1) Find Loot
                    2) Experience Town Feature
                    3) Fight Opponents
                    4) Move to Next Town
                    """);

            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    if (currentTown.loot().isEmpty()) {
                        System.out.println("You've already collected all the loot here.");
                    } else {
                        for (Loot loot : new ArrayList<>(currentTown.loot())) {
                            pirate.collectLoot(loot);
                        }
                        currentTown.loot().clear();
                    }
                }
                case 2 -> {
                    if (currentTown.features().isEmpty()) {
                        System.out.println("You've already explored the town features.");
                    } else {
                        for (Feature feature : new ArrayList<>(currentTown.features())) {
                            pirate.applyFeature(feature);
                        }
                        currentTown.features().clear();
                    }
                }
                case 3 -> {
                    for (Opponent opponent : currentTown.opponents()) {
                        opponent.useWeapon(pirate); 
                    }
                    if (pirate.getHealth() <= 0) {
                        System.out.println("💀 Your pirate has died. Game over!");
                        return;
                    }
                }
                case 4 -> {
                    if (currentTown.loot().isEmpty()) {
                        currentTownIndex++;
                        if (currentTownIndex < towns.size()) {
                            System.out.println("Sailing to the next town...");
                        } else {
                            System.out.println(" You've completed all towns!");
                            System.out.println("Final Score: " + pirate.getScore());
                        }
                    } else {
                        System.out.println("You must collect all the loot before moving on!");
                    }
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }

        scanner.close();
    }
}
