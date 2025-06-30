public class IfThenChallenge {
    public static void main(String[] args) {

        // Existing values
        boolean gameOver = true;
        int score = 10000;
        int levelCompleted = 8;
        int bonus = 200;

        // If gameOver is true, calculate finalScore
        if (gameOver) {
            int finalScore = score + (levelCompleted * bonus);
            System.out.println("Your final score was: " + finalScore);
        }
    }
    
}
