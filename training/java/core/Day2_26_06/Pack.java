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
        System.out.println(canPack(9,1, 4));     
        System.out.println(canPack(1, 0, 5));    
        System.out.println(canPack(0, 0, 4));    
        System.out.println(canPack(2, 2, 11));    
        System.out.println(canPack(-3, 2, 12));   
    }
}
