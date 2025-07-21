package models;


public class MembershipPlan {
	private int planId;
	private String planName;
	private String duration;
	private double fee;
	
	
	public MembershipPlan(int planId ,String planName, String duration, double fee) {
		this.planId=planId;
		this.planName = planName;
		this.duration = duration;
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


	public String getDuration() {
		return duration;
	}


	public void setDuration(String duration) {
		this.duration = duration;
	}


	public double getFee() {
		return fee;
	}


	public void setFee(double fee) {
		this.fee = fee;
	}
		
}

