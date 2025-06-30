package com.gym.classes;

public class Member extends Person{
	private int memberId;
	private MembershipPlan membershipPlan;
	
	
	public int getMemberId() {
		return memberId;
	}
	
	
	public int getMemberHeight() {
		return super.height;
	}
	public int getMemberWeight() {
		return super.weight;
	}
	
	public String getMemberName() {
		return super.name;
	}
	public int getMemberAge() {
		return super.age;
	}

	public MembershipPlan getMembershipPlan() {
		return membershipPlan;
	}

	public void setMemPlan(MembershipPlan memPlan) {
		this.membershipPlan = memPlan;
	}

	public Member(int memberId, String name, int age,int height, int weight) {
		this(memberId,name,age,height,weight,null);
	}
	
	public Member(int memberId, String name, int age,int height, int weight, MembershipPlan memPlan) {
		super(name,age,height,weight);
		this.memberId = memberId;
		this.name = name;
		this.age = age;
		this.membershipPlan = memPlan;
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
                    (this.getMembershipPlan() == null ? "No Plan Assigned" : this.getMembershipPlan().planName)
            );
	}
	
	
	
}
