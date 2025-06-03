package com.example;

public class Member extends Person{
	private MembershipPlan mPlan;
	private Plan plan;
	private int duration;
	private double fee;
	public Member(String GymId, String name, int age, HealthCondition healthCondition, int experience,
			List<String> foodHabit, List<String> goals,String timings, Plan plan,int duration, double fee) {
		super(GymId, name, age, healthCondition, experience, foodHabit, goals,timings);
		this.plan=plan;
		this.duration=duration;
		this.fee=fee;
		if(plan!=null) {
			this.mPlan=new MembershipPlan(plan, duration, fee);
		}
	}
	public MembershipPlan getMembershipPlan() {
		return this.mPlan;
	}
	public void setMembershipPlan(MembershipPlan mPlan) {
		this.mPlan=mPlan;
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
		return this.fee;
	}
	public void setFee(double fee) {
		this.fee=fee;
	}
	@Override
	public void display() {
		System.out.println("Gym ID : "+getGymId()+"\nName : "+getName()+"\nAge : "+getAge()+"\nHealth Condition : "+getHealthCondition()+"\nExperience : "+getExperience()+"\nFood Habit : "+getFoodHabits()+"\nGoals : "+getGoals()+"\nPlan : "+mPlan.getPlan()+"\nDuration : "+mPlan.getDuration()+" months\nTimings"+getTimings()+"\nFee : "+mPlan.getFee());
	}
}
