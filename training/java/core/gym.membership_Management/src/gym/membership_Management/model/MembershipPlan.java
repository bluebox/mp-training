package gym.membership_Management.model;

public class MembershipPlan {
	private String nameOfPlan;
	private int durationInDays;
	private double fee;
	
	public MembershipPlan(String nameOfPlan, int durationInDays, double fee) {
		this.nameOfPlan = nameOfPlan;
		this.durationInDays = durationInDays;
		this.fee = fee;
	}

	public String getNameOfPlan() {
		return nameOfPlan;
	}

	public int getDurationInDays() {
		return durationInDays;
	}

	public double getFee() {
		return fee;
	}	
	
}
