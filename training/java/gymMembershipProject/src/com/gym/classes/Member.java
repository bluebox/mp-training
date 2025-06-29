package com.gym.classes;

public class Member extends Person{
	int memberId;
	MembershipPlan memPlan;
	
	
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
	
	
	
}
