package models;

public class MembershipPlans {
	private int id;
	private String PlanName;
	private int durationMonths;
	private double fee;

	public MembershipPlans(int id, String planName, int durationMonths, double fee) {
		super();
		this.id = id;
		PlanName = planName;
		this.durationMonths = durationMonths;
		this.fee = fee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlanName() {
		return PlanName;
	}

	public void setPlanName(String planName) {
		PlanName = planName;
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
		return "MembershipPlans [id=" + id + ", PlanName=" + PlanName + ", durationMonths=" + durationMonths + ", fee="
				+ fee + "]";
	}

}
