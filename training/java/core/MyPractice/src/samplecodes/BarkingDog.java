package samplecodes;

public class BarkingDog {
	public static void main(String[] args) {
		System.out.print(shouldWakeUp(false,2));
	}
	public static boolean shouldWakeUp(boolean barking , int hourOfDay) {
		if(hourOfDay<0 || hourOfDay>23) return false;
		if(hourOfDay<8 || hourOfDay>22) return true && barking;
		return false;
	}
}
