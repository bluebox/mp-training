package GymManagementSystem.models;

public class MembershipPlan {
	private int planId;
	private String planName;
	private int durationMonths;
	private double fee;

	public MembershipPlan(int planId, String planName, int durationMonths, double fee) {
		this.planId = planId;
		this.planName = planName;
		this.durationMonths = durationMonths;
		this.fee = fee;
	}

	public MembershipPlan(String planName, int durationMonths, double fee) {
		this.planName = planName;
		this.durationMonths = durationMonths;
		this.fee = fee;
	}
	
	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public int getDurationMonths() {
		return durationMonths;
	}

	public void setDurationMonths(int durationMonths) {
		this.durationMonths = durationMonths;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public void printDetails() {
		//return "planId=" + planId + ", planName=" + planName + ", durationMonths=" + durationMonths + ", fee=" + fee;
		System.out.println("--------------------------------------------------");
		System.out.println("Plan ID    : " + planId);
		System.out.println("Plan Name  : " + getPlanName());
		System.out.println("Duration   : " + getDurationMonths());
		System.out.println("Fees       : " + getFee());
		System.out.println("--------------------------------------------------");
	}

	@Override
	public String toString() {
		//return "MembershipPlan [planId=" + planId + ", planName=" + planName + ", durationMonths=" + durationMonths + ", fee=" + fee + "]";
		return "Plan ID: " + planId +
	               ", Name: " + planName +
	               ", Fee: ₹" + fee +
	               ", Duration: " + durationMonths + " months";
	}
	
	

}
