package models;

public class MembershipPlan {

	private int id;
	private String planName;
	private int durationMonths;
	private double fee;

	public MembershipPlan(int id, String planName, int durationMonths, double fee) {
		this.id = id;
		this.planName = planName;
		this.durationMonths = durationMonths;
		this.fee = fee;
	}

	public MembershipPlan(String planName, int durationMonths, double fee) {
		this(-1, planName, durationMonths, fee);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return "MembershipPlan [id=" + id + ", planName=" + planName + ", durationMonths=" + durationMonths + ", fee="
				+ fee + "]";
	}

}
