package com.gym.domain;

class MemberShipPlan {

	PlanDetails plan;
	private int duration;
	private double fee;

	public MemberShipPlan(PlanDetails plan, int duration) {
		super();
		this.plan = plan;
		this.duration = duration;
		this.fee = plan.price * duration;
	}

	public void getPlanDetails() {
		System.out.println("MemberShipPlan Details\nPlan Name=" + plan.name() + 
				"\nduration=" + duration + 
				"\nTotal fee=" + fee );
	}

}
