package day2;

public class FlourPacker {

    public static void main(String[] args) {
        System.out.println(canPack(1, 0, 4));   // false
        System.out.println(canPack(1, 0, 5));   // true
        System.out.println(canPack(0, 5, 4));   // true
        System.out.println(canPack(2, 2, 11));  // true
        System.out.println(canPack(-3, 2, 12)); // false
    }

    public static boolean canPack(int bigCount, int smallCount, int goal) {
        if (bigCount < 0 || smallCount < 0 || goal < 0) {
            return false;
        }

        // Use as many big bags as possible without exceeding the goal
        int maxBigBags = goal / 5;
        int bigBagsToUse = Math.min(maxBigBags, bigCount);

        // Remaining weight after using big bags
        int remaining = goal - (bigBagsToUse * 5);

        // Check if small bags can cover the remaining weight
        return smallCount >= remaining;
    }
}