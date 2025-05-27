package conditions;

public class BarkingDog {

		public static boolean shouldWakeUp(boolean isBarking, int hours) {
	        if (hours < 0 || hours > 23) {
	            return false;
	        }

	        return isBarking && (hours< 8 || hours > 22);
	    }
		
		public static void main(String[] args) {
	        System.out.println(BarkingDog.shouldWakeUp(true, 1));   
	        System.out.println(BarkingDog.shouldWakeUp(false, 2));  
	        System.out.println(BarkingDog.shouldWakeUp(true, 8));   
	        System.out.println(BarkingDog.shouldWakeUp(true, -1));  
	        System.out.println(BarkingDog.shouldWakeUp(true, 23));  
	    }

}
