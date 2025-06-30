package Gym_Membership_Management_System;

public class MembershiPlan {
	private String planName;
	private int durationMonths;
	private double fee;
	
	public MembershiPlan(String planName, int durationMonths, double fee) {
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
    public void showPlanDetails() {
        System.out.println("Plan: " + planName + ", Duration: " + durationMonths + " months, Fee: ₹" + fee);
    }

    @Override
    public String toString() {
        return planName + " Plan (" + durationMonths + " months, ₹" + fee + ")";
    }
	
}
