package com.example;

public class MembershipPlan {
	private Plan plan;
	private int duration;
	private double fee;
	public MembershipPlan(Plan plan,int duration,double fee) {
		this.plan=plan;
		this.duration=duration;
		this.fee=fee;
	}
	public Plan getPlan() {
		return this.plan;
	}
	public void setPlan(Plan plan) {
		this.plan=plan;
	}
	public int getDuration() {
		return this.duration;
	}
	public void setDuration(int duration) {
		this.duration=duration;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee=fee;
	}
}