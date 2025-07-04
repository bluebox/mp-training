package com.gym.classes;

public class Member extends Person{
	private int memberId;
	private MembershipPlan membershipPlan;
	private String joinDate;
	
	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

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
		return this.membershipPlan;
	}

	public void setMemPlan(MembershipPlan memPlan) {
		this.membershipPlan = memPlan;
	}

	public Member(int memberId, String name, int age,int height, int weight) {
		this(memberId,name,age,height,weight,null,"NA");
	}
	
	public Member(int memberId, String name, int age,int height, int weight,String joinDate) {
		this(memberId,name,age,height,weight,null,joinDate);
	} 
	
	public Member(int memberId, String name, int age,int height, int weight, MembershipPlan memPlan , String joinDate) {
		super(name,age,height,weight);
		this.memberId = memberId;
		this.name = name;
		this.age = age;
		this.membershipPlan = memPlan;
		this.joinDate = joinDate;
	}


	//Polymorphism
	@Override
	public void showDetails() {
		// TODO Auto-generated method stub
        System.out.printf(
                    "ID:%d | Name: %s | Age: %d | Plan: %s "
                    + (this.joinDate.equals("NA") ? "\n": "| Joining Date: %s\n"),
                    this.getMemberId(),
                    this.name,
                    this.age,
                    (this.getMembershipPlan() == null ? "No Plan Assigned" : this.getMembershipPlan().planName),
                    this.joinDate.equals("NA") ? "" : joinDate
            );
	}
	
	
	
}
