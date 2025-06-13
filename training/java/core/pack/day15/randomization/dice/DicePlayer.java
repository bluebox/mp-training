package day15.randomization.dice;

import day15.randomization.game.GameConsole;



import day15.randomization.game.Player;

import java.util.*;

public class DicePlayer implements Player {

    private final String name;
    private final List<Integer> currentDice = new ArrayList<>();
    private final Map<ScoredItem,Integer> scoreCard = new EnumMap<>(ScoredItem.class);

    public DicePlayer(String name) {
        this.name = name;
        for (ScoredItem item : ScoredItem.values()) {
            scoreCard.put(item, null);
        }

    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return "DicePlayer{" +
                "name='" + name + '\'' +
                ", currentDice=" + currentDice +
                ", scoreCard=" + scoreCard +
                '}';
    }

    public void rollDice() {

        int randomCount = 5 - currentDice.size();

        var newDice = new Random()
                .ints(randomCount, 1, 7)
                .sorted()
                .boxed()
                .toList();

        currentDice.addAll(newDice);

        System.out.println("You're dice are: " + currentDice);

    }

    private boolean pickLosers() {

        String prompt = """
                    Press Enter to Score.
                    Type "ALL" to re-roll all the dice.
                    List numbers (separated by spaces) to re-roll selected dice.
                        """;

        String userInput = GameConsole.getUserInput(prompt +  "--> ");
        if (userInput.isBlank()) {
            return true;
        }
        try {
            removeDice(userInput.split(" "));

        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("Bad input, Try again");
        }
        return false;
    }

    private void removeDice(String[] selected) {

        if (selected.length == 1 && selected[0].contains("ALL")) {
            currentDice.clear();
        } else {
            for (String removed : selected) {
                currentDice.remove(Integer.valueOf(removed));
            }
            System.out.println("Keeping " + currentDice);
        }
    }

    public boolean rollDiceAndSelect() {

        do {
            rollDice();
        } while (!pickLosers());

        do {
            System.out.println("You must select a score category:");
        } while (!scoreDice());

        currentDice.clear();
        return (getItemList().size() == 0);
    }

    public List<String> getItemList() {

        return scoreCard
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == null)
                .map(e -> e.getKey().name())
                .toList();
    }

    private boolean scoreDice() {

        List<String> remainingItems = getItemList();
        String prompt = String.join("\n", remainingItems.toArray(new String[0]));
        String userInput =
                GameConsole.getUserInput(prompt + "\n-->  ").toUpperCase();
        if (userInput.isBlank()) {
            return false;
        }

        if (!remainingItems.contains(userInput)) {
            System.out.println("Invalid selection");
            return false;
        }
        ScoredItem item = ScoredItem.valueOf(userInput);
        scoreCard.put(item, item.score(currentDice));
        return true;
    }
}
