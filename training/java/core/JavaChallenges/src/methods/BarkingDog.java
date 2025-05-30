package methods;

import java.util.Scanner;

public class BarkingDog {

		public static boolean shouldWakeUp(boolean isBarking, int hours) {
	        if (hours < 0 || hours > 23) {
	            return false;
	        }

	        return isBarking && (hours< 8 || hours > 22);
	    }
		
		public static void main(String[] args) {
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter the true or false:");
			boolean isBarking=sc.nextBoolean();
			System.out.println("Enter the hours:");
			int hours=sc.nextInt();
	        System.out.println(BarkingDog.shouldWakeUp(isBarking,hours));   
	       
	    }

}