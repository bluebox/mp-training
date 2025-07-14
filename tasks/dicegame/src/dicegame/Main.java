package dicegame;
import java.util.*;

public class Main {
    private static final Random random = new Random();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        List<Integer> currentDice = new ArrayList<>();

        do {
            rollDice(currentDice);
        } while (!pickLosers(currentDice));

        System.out.println("Game over. Final dice: " + currentDice);
    }

    private static void rollDice(List<Integer> currentDice) {
        int randomCount = 5 - currentDice.size();
        List<Integer> newDice = random
                .ints(randomCount, 1, 7) 
                .boxed()
                .toList();

        currentDice.addAll(newDice);
        Collections.sort(currentDice);

        System.out.println("Current Dice: " + currentDice);
    }

    public static void removeDice(List<Integer> currentDice, String[] userInput) {
        if (userInput.length == 1 && userInput[0].equalsIgnoreCase("ALL")) {
            currentDice.clear();
        } else {
            for (String s : userInput) {
                try {
                    int value = Integer.parseInt(s);
                    if (!currentDice.remove(Integer.valueOf(value))) {
                        System.out.println(" Value " + value + " not found in dice!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid number: " + s);
                }
            }
        }
    }

    public static boolean pickLosers(List<Integer> currentDice) {
        System.out.println("""
                
                 Press Enter to Score.
                 Type "ALL" to re-roll all the dice.
                 List numbers (separated by spaces) to re-roll selected dice.
                """);

        String userInput = sc.nextLine();

        if (userInput.isBlank()) {
            return true; 
        }

        try {
            removeDice(currentDice, userInput.trim().split("\\s+"));
        } catch (Exception e) {
            System.out.println("bad input try agian");
            e.printStackTrace(System.out);
        }

        return false;
    }
}

