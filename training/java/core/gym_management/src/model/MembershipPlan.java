package model;

public class MembershipPlan {
	private int id;
	private String nameOfPlan;
	private int durationInDays;
	private double fee;

	public MembershipPlan(String nameOfPlan, int durationInDays, double fee) {
		this.nameOfPlan = nameOfPlan;
		this.durationInDays = durationInDays;
		this.fee = fee;
	}
	public MembershipPlan(int id,String nameOfPlan, int durationInDays, double fee) {
		this.id=id;
		this.nameOfPlan = nameOfPlan;
		this.durationInDays = durationInDays;
		this.fee = fee;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setNameOfPlan(String nameOfPlan) {
		this.nameOfPlan = nameOfPlan;
	}
	
	public String getNameOfPlan() {
		return nameOfPlan;
	}
	
	public void setDurationInDays(int durationInDays) {
		this.durationInDays = durationInDays;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}
	
	public int getDurationInDays() {
		return durationInDays;
	}

	public double getFee() {
		return fee;
	}	
	
}
