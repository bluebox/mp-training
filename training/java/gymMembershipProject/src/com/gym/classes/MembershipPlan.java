package com.gym.classes;

public class MembershipPlan {
	String planName;
	int durationMonths;
	int fee;
	
	public MembershipPlan(String planName, int durationMonths, int fee) {
		this.planName = planName;
		this.durationMonths = durationMonths;
		this.fee = fee;
	}

	@Override
	public String toString() {
		return "Membership Plan=" + planName + ", Duration=" + durationMonths + " Months "+ ", fee=" + fee;
	}
	
	

	
}
