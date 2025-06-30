package com.gym.classes;

public class Member extends Person{
	private int memberId;
	private MembershipPlan memPlan;
	
	
	public int getMemberId() {
		return memberId;
	}

	

	public MembershipPlan getMemPlan() {
		return memPlan;
	}

	public void setMemPlan(MembershipPlan memPlan) {
		this.memPlan = memPlan;
	}

	public Member(int memberId, String name, int age,int height, int weight) {
		this(memberId,name,age,height,weight,null);
	}
	
	public Member(int memberId, String name, int age,int height, int weight, MembershipPlan memPlan) {
		super(name,age,height,weight);
		this.memberId = memberId;
		this.name = name;
		this.age = age;
		this.memPlan = memPlan;
	}


	//Polymorphism
	@Override
	public void showDetails() {
		// TODO Auto-generated method stub
        System.out.printf(
                    "ID:%d | Name: %s | Age: %d | Plan: %s\n",
                    this.getMemberId(),
                    this.name,
                    this.age,
                    (this.getMemPlan() == null ? "No Plan Assigned" : this.getMemPlan().planName)
            );
	}
	
	
	
}
