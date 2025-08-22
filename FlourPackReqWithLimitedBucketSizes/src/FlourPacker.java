
public class FlourPacker {
	public static boolean pack(int bigCount,int smallCount,int goal) {
		if(bigCount<0 || smallCount<0 || goal<0) {
			return false;
		}
		
		int bigBags=goal/5;
		int bigBagsToUse=Math.min(bigCount, bigBags);
		int remainingKgs=goal-(bigBagsToUse*5);
		
		return smallCount>=remainingKgs;
		
	}
}
