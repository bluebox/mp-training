package dicegame;



import java.util.*;
import java.util.stream.Collectors;

public class DiceGame {
    private final List<Integer> dice = new ArrayList<>();
    private final Random random = new Random();

    public void play() {
        Scanner scanner = new Scanner(System.in);

        rollAllDice(); 
        while (true) {
            displayDice();
            System.out.println("""
                Press Enter to keep all dice.
                Type "ALL" to reroll all dice.
                Or enter values (separated by spaces) of dice to reroll:
            """);

            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Game over, final dice: " + dice);
                break;
            }

            if (input.equalsIgnoreCase("ALL")) {
                rollAllDice();
            } else {
                rerollSelectedDice(input);
            }
        }

        scanner.close();
    }

    private void rollAllDice() {
        dice.clear();
        random.ints(5, 1, 7).forEach(dice::add);
    }

    private void rerollSelectedDice(String input) {
        String[] tokens = input.split("\\s+");
        Set<Integer> valuesToReroll = new HashSet<>();

        for (String token : tokens) {
            try {
                int value = Integer.parseInt(token);
                valuesToReroll.add(value);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: " + token);
            }
        }

        for (int i = 0; i < dice.size(); i++) {
            int currentVal = dice.get(i);
            if (valuesToReroll.contains(currentVal)) {
                int newVal = random.nextInt(6) + 1;
                dice.set(i, newVal);
                valuesToReroll.remove(currentVal); 
            }
        }
    }

    private void displayDice() {
        String diceStr = dice.stream()
                             .map(String::valueOf)
                             .collect(Collectors.joining(", "));
        System.out.println("Your dice are: " + diceStr);
    }
}
