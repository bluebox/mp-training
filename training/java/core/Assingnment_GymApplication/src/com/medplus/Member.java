package com.medplus;

import java.time.LocalDate;

public class Member extends Person{
	private int memberId;
	private LocalDate StartDate;
	private MembershipPlan plan;

	public Member(int memberId,String name, int age,LocalDate StartDate,MembershipPlan plan) {
		super(name, age);
		this.memberId = memberId;
		this.StartDate = StartDate;
		this.plan = plan;
		
	}

	public void getPersonDetails() {
		System.out.printf("%n%20s : %d".formatted("Your Membership Id",memberId));
		System.out.printf("%n%20s : %s".formatted("Name",super.getName()));
		System.out.printf("%n%20s : %d".formatted("Age",super.getAge()));
		System.out.printf("%n%20s : %s".formatted("Membership",plan.name()));
		System.out.printf("%n%20s : %s".formatted("StartDate",StartDate));
	}
	

}
