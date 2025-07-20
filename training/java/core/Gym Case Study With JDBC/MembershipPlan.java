package gymCaseStudyWithJdbc;

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

	public int getPlanId() {
		return planId;
	}

	public String getPlanName() {
		return planName;
	}

	public int getDurationMonths() {
		return durationMonths;
	}

	public double getFee() {
		return fee;
	}

	@Override
	public String toString() {
		return "MembershipPlan [planId=" + planId + ", planName=" + planName + ", durationMonths=" + durationMonths
				+ ", fee=" + fee + "]";
	}

}