public class PlayingCatProblem {
    public static boolean isCatPlaying(boolean summer, int temperature) {
        int upperLimit = summer ? 45 : 35;
        return temperature >= 25 && temperature <= upperLimit;
    }

    public static void main(String[] args) {
        // Test cases from the instructions
        System.out.println(isCatPlaying(true, 10));   // false
        System.out.println(isCatPlaying(false, 36));  // false
        System.out.println(isCatPlaying(false, 35));  // true
    }
}
