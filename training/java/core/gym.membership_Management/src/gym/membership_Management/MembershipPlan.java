package gym.membership_Management;

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

	public void setNameOfPlan(String nameOfPlan) {
		this.nameOfPlan = nameOfPlan;
	}

	public int getDurationInDays() {
		return durationInDays;
	}

	public void setDurationInDays(int durationInDays) {
		this.durationInDays = durationInDays;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}
	
	
}
