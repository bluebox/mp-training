package com.medplus;

import java.time.LocalDate;

public class Member extends Person {

	private int memberId;
	private MembershipPlans plan;
	private LocalDate StartDate;
	private LocalDate EndDate;

	public Member(int memberId, String name, int age, LocalDate StartDate, MembershipPlans plan) {
		super(name, age);
		this.memberId = memberId;
		this.StartDate = StartDate;
		this.plan = plan;
		this.EndDate = StartDate.plusMonths(1);

	}

	public LocalDate getEndDate() {
		return EndDate;
	}

	public void setEndDate(LocalDate endDate) {
		EndDate = endDate;
	}

	public void getPersonDetails() {
		System.out.printf("%n%20s : %d".formatted("Your Membership Id", memberId));
		System.out.printf("%n%20s : %s".formatted("Name", super.getName()));
		System.out.printf("%n%20s : %d".formatted("Age", super.getAge()));
		System.out.printf("%n%20s : %s".formatted("Membership", plan.name()));
		System.out.printf("%n%20s : %s".formatted("StartDate", StartDate));
		System.out.printf("%n%20s : %s".formatted("EndDate", EndDate));
		if (LocalDate.now().isAfter(EndDate)) {
			System.out.printf("%n%20s : %s".formatted("Membership Status", "Inactive"));
		} else {
			System.out.printf("%n%20s : %s".formatted("Membership Status", "Active"));
		}
	}

	public int getMemberId() {
		return memberId;
	}

	public MembershipPlans getPlan() {
		return plan;
	}

	public void setPlan(MembershipPlans plan) {
		this.plan = plan;
	}

	public LocalDate getStartDate() {
		return StartDate;
	}

	public void setStartDate(LocalDate startDate) {
		StartDate = startDate;
	}

	public String getName() {
		return super.getName();
	}

	public int getAge() {
		return super.getAge();

	}

	public String getStatus() {
		if (LocalDate.now().isAfter(EndDate)) {
			return "Inactive";
		} else {
			return "Active";
		}
	}
}
