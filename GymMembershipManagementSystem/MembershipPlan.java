package GymMembershipManagementSystem;

public class MembershipPlan {

	private String planName;
    private int durationMonths;
    private double fee;
    
	public MembershipPlan(String planName, int durationMonths, double fee) {
		this.planName = planName;
		this.durationMonths = durationMonths;
		this.fee = fee;
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
    
	@Override
	public String toString() {
		return "MembershipPlan [planName=" + planName + ", durationMonths=" + durationMonths + ", fee=" + fee + "]";
	}
	
}
