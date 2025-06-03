package gym;

public class Plan {
	private String planName;
	private int durationMonths;
	private int fee;
	
	
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


	public int getFee() {
		return fee;
	}


	public void setFee(int fee) {
		this.fee = fee;
	}


	public Plan(String planName, int durationMonths, int fee) {
		super();
		this.planName = planName;
		this.durationMonths = durationMonths;
		this.fee = fee;
	}
	

}
