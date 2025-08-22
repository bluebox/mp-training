package com.prana;

import java.util.*;
import java.util.stream.*;

public class MathOp {

    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
    	// TODO Auto-generated method stub
        List<Integer> dice = rollDice(5);  
        while (true) {
            System.out.println("Your dice: " + dice);
            System.out.println("""
                Press Enter to score.
                Type "ALL" to re-roll all dice.
                List number (separated by spaces) to re-roll selected dice.
                --->""");
            String input = scanner.nextLine().trim();
            if (input.isBlank()) {
                System.out.println("Final Dice: " + dice);
                break;
            }

            if (input.equalsIgnoreCase("ALL")) {
                dice = rollDice(5);
            } else {
                try {
                    Set<Integer> toReroll = Arrays.stream(input.split("\\s+"))
                             .map(Integer::parseInt)
                             .collect(Collectors.toSet());
                    for (int i = 0; i < dice.size(); i++) {
                        if (toReroll.contains(dice.get(i))) {
                            dice.set(i, random.nextInt(6) + 1);
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter numbers or 'ALL'.");
                }
            }
        }
        System.out.println("Game Over.");
    }

    private static List<Integer> rollDice(int count) {
        return random.ints(count, 1, 7)
                     .boxed()
                     .collect(Collectors.toList());
    }
}