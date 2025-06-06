package com.gym.domain;

public class MemberShipPlan {

	private PlanDetails plan;
	private int duration;
	private double fee;

	public MemberShipPlan(PlanDetails plan, int duration) {
		this.plan = plan;
		this.duration = duration;
		this.fee = plan.getPrice() * duration;
	}

	public PlanDetails getPlan() {
		return plan;
	}

	public int getDuration() {
		return duration;
	}

	public double getFee() {
		return fee;
	}

	public void setPlan(PlanDetails plan) {
		this.plan = plan;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	
	@Override
	public String toString() {
		return "MemberShipPlan Details\nPlan Name=" + plan.name() + 
				"\nduration=" + duration + 
				"\nTotal fee=" + fee ;
	}


}
