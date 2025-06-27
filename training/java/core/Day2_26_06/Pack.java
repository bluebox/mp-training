package Day2_26_06;

public class Pack {
	public static boolean canPack(int bigCount, int smallCount, int goal) {
        if (bigCount < 0 || smallCount < 0 || goal < 0) {
            return false;
        }

        int maxBigBagsUsable = goal / 5;
        int bigBagsToUse = Math.min(bigCount, maxBigBagsUsable);

        int remainingWeight = goal - (bigBagsToUse * 5);

        return smallCount >= remainingWeight;
    }

    public static void main(String[] args) {
        System.out.println(canPack(1, 9, 4));     // false
        System.out.println(canPack(1, 0, 5));     // true
        System.out.println(canPack(0, 5, 4));     // true
        System.out.println(canPack(2, 2, 11));    // true
        System.out.println(canPack(-3, 2, 12));   // false
    }
}
