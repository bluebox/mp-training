package june26_methods;

import java.util.Scanner;

public class FlourPacker {

    public static boolean canPack(int bigCount, int smallCount, int goal) {
        if (bigCount < 0 || smallCount < 0 || goal < 0) {
            return false;
        }

        int maxBigBags = goal / 5;
        int usedBigBags = Math.min(maxBigBags, bigCount);

        int remaining = goal - (usedBigBags * 5);

        return remaining <= smallCount;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter bigCount (number of 5kg bags): ");
        int bigCount = scanner.nextInt();

        System.out.print("Enter smallCount (number of 1kg bags): ");
        int smallCount = scanner.nextInt();

        System.out.print("Enter goal (in kg): ");
        int goal = scanner.nextInt();

        boolean result = canPack(bigCount, smallCount, goal);

        if (result) {
            System.out.println("Yes, it is possible to pack the flour.");
        } else {
            System.out.println("No, it is NOT possible to pack the flour.");
        }

        scanner.close();
    }
}
