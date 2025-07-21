package model;

public class MembershipPlan {

	public String planId;
	public String planName;
	public int duration_in_months;
	public double fee;
	public MembershipPlan(String planId, String planName, int duration_in_months, double fee) {
		this.planId = planId;
		this.planName = planName;
		this.duration_in_months = duration_in_months;
		this.fee = fee;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public int getDuration_in_months() {
		return duration_in_months;
	}
	public void setDuration_in_months(int duration_in_months) {
		this.duration_in_months = duration_in_months;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	@Override
	public String toString() {
		return "MembershipPlan [planId=" + planId + ", planName=" + planName + ", duration_in_months="
				+ duration_in_months + ", fee=" + fee + "]";
	}
	
	
	
}
