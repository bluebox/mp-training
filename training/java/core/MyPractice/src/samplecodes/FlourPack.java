package samplecodes;

public class FlourPack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(canPack(3,4,12));
	}
	public static boolean canPack(int big,int small, int goal) {
		if(small>=goal) return true;
		if((big*5)==goal) return true;
		int q=goal/5;
		int req=goal%5;
		return false;
	}

}
