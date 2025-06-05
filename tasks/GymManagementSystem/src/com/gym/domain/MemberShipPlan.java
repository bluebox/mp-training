package com.gym.domain;

class MemberShipPlan {

	PlanDetails plan;
	private int duration;
	private double fee;

	public MemberShipPlan(PlanDetails plan, int duration) {
		this.plan = plan;
		this.duration = duration;
		this.fee = plan.getPrice() * duration;
	}

	public void getPlanDetails() {
		System.out.println("MemberShipPlan Details\nPlan Name=" + plan.name() + 
				"\nduration=" + duration + 
				"\nTotal fee=" + fee );
	}

}
