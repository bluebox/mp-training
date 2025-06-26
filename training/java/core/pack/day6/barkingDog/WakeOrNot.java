package day6.barkingDog;

public class WakeOrNot {
	public  boolean shouldWakeUp(boolean barking,int time) {
		boolean hasToWake=false;
		if((time<8 || time>22)&& barking) 
			hasToWake=true; 
		else 
			hasToWake=false;
		
		return hasToWake;
			
		
	}
}
