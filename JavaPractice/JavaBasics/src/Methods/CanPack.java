package Methods;

import java.util.Scanner;

public class CanPack {

    public static boolean canPack(int bigCount, int smallCount, int goal) {
        if (bigCount < 0 || smallCount < 0 || goal < 0) {
            return false;
        }
        int bigBagsUsed = goal / 5;
        if (bigBagsUsed > bigCount) {
            bigBagsUsed = bigCount;
        }
        int remainingKilos = goal - (bigBagsUsed * 5);
        return remainingKilos <= smallCount;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int bigCount = scanner.nextInt();
        int smallCount = scanner.nextInt();
        int goal = scanner.nextInt();
        boolean result = canPack(bigCount, smallCount, goal);
        System.out.println(result);
        scanner.close();
    }
}
