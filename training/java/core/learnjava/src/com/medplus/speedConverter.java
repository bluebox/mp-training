package com.medplus;

public class speedConverter {
	
	speedConverter(double speedInKmph){
		double speedInMlph = speedInKmph/1.609;
		System.out.println("Speed of "+speedInKmph+" in kmph is equals to " + Math.round(speedInMlph) + " in miles/hour");
	}


	public static void main(String[] args) {
		double speed = 10.08;
		speedConverter s = new speedConverter(speed);
	}
}

