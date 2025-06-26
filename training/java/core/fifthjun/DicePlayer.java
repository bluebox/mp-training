package fifthjun;

import java.util.*;
import java.util.stream.*;

public class DicePlayer {
    private List<Integer> dice;
    private Set<String> usedCategories;

    public DicePlayer() {
        dice = new ArrayList<>();
        usedCategories = new HashSet<>();
    }

    public void rollDice() {
        Random random = new Random();
        dice = IntStream.range(0, 5)
                       .map(i -> random.nextInt(6) + 1)
                       .boxed()
                       .collect(Collectors.toList());
        System.out.println("Dice: " + dice);
    }

    public void displayAvailableCategories() {
        System.out.println("You must select a score category:");
        List<String> categories = Arrays.asList(
            "ACES", "TWOS", "THREES", "FOURS", "FIVES", "SIXES",
            "THREE_OF_KIND", "FOUR_OF_KIND", "FULL_HOUSE",
            "SMALL_STRAIGHT", "LARGE_STRAIGHT", "FIVE_OF_KIND"
        );
        categories.stream()
                 .filter(c -> !usedCategories.contains(c))
                 .forEach(System.out::println);
    }

    public int score(String category) {
        if (usedCategories.contains(category)) return 0;
        usedCategories.add(category);

        switch (category) {
            case "ACES": return countDice(1);
            case "TWOS": return countDice(2) * 2;
            case "THREES": return countDice(3) * 3;
            case "FOURS": return countDice(4) * 4;
            case "FIVES": return countDice(5) * 5;
            case "SIXES": return countDice(6) * 6;
            case "THREE_OF_KIND": return hasNOfKind(3) ? sumDice() : 0;
            case "FOUR_OF_KIND": return hasNOfKind(4) ? sumDice() : 0;
            case "FIVE_OF_KIND": return hasNOfKind(5) ? 50 : 0;
            case "FULL_HOUSE": return isFullHouse() ? 25 : 0;
            case "SMALL_STRAIGHT": return isSmallStraight() ? 30 : 0;
            case "LARGE_STRAIGHT": return isLargeStraight() ? 40 : 0;
            default: return 0;
        }
    }

    private int countDice(int value) {
        return (int) dice.stream().filter(d -> d == value).count();
    }

    private boolean hasNOfKind(int n) {
        return dice.stream()
                   .anyMatch(d -> Collections.frequency(dice, d) >= n);
    }

    private int sumDice() {
        return dice.stream().mapToInt(Integer::intValue).sum();
    }

    private boolean isFullHouse() {
        Map<Integer, Long> counts = dice.stream()
                                      .collect(Collectors.groupingBy(
                                          d -> d, Collectors.counting()));
        return counts.containsValue(3L) && counts.containsValue(2L);
    }

    private boolean isSmallStraight() {
        List<Integer> sorted = dice.stream().distinct().sorted().collect(Collectors.toList());
        return containsSequence(sorted, 4);
    }

    private boolean isLargeStraight() {
        List<Integer> sorted = dice.stream().distinct().sorted().collect(Collectors.toList());
        return sorted.size() == 5 && (sorted.get(4) - sorted.get(0) == 4);
    }

    private boolean containsSequence(List<Integer> list, int length) {
        if (list.size() < length) return false;
        for (int i = 0; i <= list.size() - length; i++) {
            if (list.get(i + length - 1) - list.get(i) == length - 1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        DicePlayer player = new DicePlayer();
        player.rollDice();
        player.displayAvailableCategories();
        Scanner scanner = new Scanner(System.in);
        String category = scanner.nextLine().trim();
        int score = player.score(category);
        System.out.println("Score for " + category + ": " + score);
    }
}