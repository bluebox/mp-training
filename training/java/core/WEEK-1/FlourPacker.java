public class FlourPacker {

    public static boolean canPack(int bigCount, int smallCount, int goal) {
        if (bigCount < 0 || smallCount < 0 || goal < 0) {
            return false;
        }

        int maxBigBags = goal / 5;
        int bigBagsUsed = Math.min(bigCount, maxBigBags);
        int remaining = goal - (bigBagsUsed * 5);

        return smallCount >= remaining;
    }

    public static void main(String[] args) {
        // Test cases from the image
        System.out.println(canPack(1, 0, 4));     // false
        System.out.println(canPack(1, 0, 5));     // true
        System.out.println(canPack(0, 5, 4));     // true
        System.out.println(canPack(2, 2, 11));    // true
        System.out.println(canPack(-3, 2, 12));   // false
    }
}
