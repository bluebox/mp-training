package gym;

class MemberShipPlan {

	PlanDetails plan;
	int duration;
	double fee;

	public MemberShipPlan(PlanDetails plan, int duration) {
		super();
		this.plan = plan;
		this.duration = duration;
		this.fee = plan.price * duration;
	}

	public void getPlanDetails() {
		System.out.println("MemberShipPlan [name=" + plan.name() + ", duration=" + duration + ", fee=" + fee + "]");
	}

}
