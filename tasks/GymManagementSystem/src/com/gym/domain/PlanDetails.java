package com.gym.domain;

public enum PlanDetails {

	BASIC(800,1), GOLD(1200,1), PREMINUM(2000,1);

	 public int price;
	 public int duration;

	PlanDetails(int price,int duration) {
		this.price = price;
		this.duration=duration;

	}

}
