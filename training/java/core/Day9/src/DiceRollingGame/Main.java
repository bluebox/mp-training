package DiceRollingGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    static Random random = new Random();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        List<Integer> currentDice = new ArrayList<>();

        do {
            rollDice(currentDice);
        } while (!askUser(currentDice));

        System.out.println("Game Over.");
    }

    static void rollDice(List<Integer> currentDice) {
        int neededDice = 5 - currentDice.size();

        List<Integer> newDice = random.ints(neededDice, 1, 7)
                .boxed()
                .collect(Collectors.toList());

        currentDice.addAll(newDice);

        System.out.println("Your dice are: " + currentDice);
    }

    static boolean askUser(List<Integer> currentDice) {
        System.out.println("\nPress Enter to Stop.");
        System.out.println("Type 'ALL' to re-roll all dice.");
        System.out.println("Type dice numbers to re-roll selected dice (e.g., 2 5 6):");
        System.out.print(">> ");

        String input = scanner.nextLine();

        if (input.isBlank()) {
            return true;
        }

        try {
            handleInput(currentDice, input.split(" "));
        } catch (Exception e) {
            System.out.println("Oops! Invalid input. Try again.");
        }

        return false;
    }

    static void handleInput(List<Integer> currentDice, String[] selected) {
        if (selected.length == 1 && selected[0].equalsIgnoreCase("ALL")) {
            currentDice.clear(); 
        } else {
            for (String value : selected) {
                try {
                    int num = Integer.parseInt(value);
                    currentDice.remove(Integer.valueOf(num));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number: " + value);
                }
            }
            System.out.println("Keeping: " + currentDice);
        }
    }
}