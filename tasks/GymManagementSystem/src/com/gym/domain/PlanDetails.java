package com.gym.domain;

public enum PlanDetails {

	BASIC(800,1), GOLD(1200,1), PREMINUM(2000,1);

	 private int price;
	 private int duration;
	 private PlanDetails(int price,int duration) {
		 this.price = price;
		 this.duration=duration;
		 
	 }
	public int getPrice() {
		return price;
	}


	public int getDuration() {
		return duration;
	}



}
