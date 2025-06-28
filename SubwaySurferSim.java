import java.util.Scanner;
import java.util.Random;

public class SubwaySurferSim {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int lane = 1; // 0: Left, 1: Middle, 2: Right
        int score = 0;
        boolean gameOn = true;

        System.out.println("🚨 Subway Surfer Game Started 🚨");
        System.out.println("Controls: A (left), D (right), Q (quit)");

        while (gameOn) {
            System.out.println("\n🏃‍♂️ Running... Lane: " + (lane == 0 ? "Left" : lane == 1 ? "Middle" : "Right"));
            boolean obstacle = random.nextInt(4) == 0;
            int obstacleLane = random.nextInt(3);

            if (obstacle) {
                System.out.println("⚠️ Obstacle ahead in lane: " + (obstacleLane == 0 ? "Left" : obstacleLane == 1 ? "Middle" : "Right"));
                if (obstacleLane == lane) {
                    System.out.println("💥 CRASH! Game Over!");
                    gameOn = false;
                    break;
                } else {
                    System.out.println("✅ You dodged the obstacle!");
                }
            }

            score++;
            System.out.print("Press A/D to move or Q to quit: ");
            String move = scanner.nextLine().toLowerCase();

            switch (move) {
                case "a":
                    if (lane > 0) lane--;
                    break;
                case "d":
                    if (lane < 2) lane++;
                    break;
                case "q":
                    gameOn = false;
                    break;
                default:
                    System.out.println("❓ Invalid input! Use A, D, or Q.");
            }
        }
        scanner.close();
        System.out.println("\n🏁 Game Off. Your final score: " + score);
    }
}
