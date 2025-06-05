package com.gym.domain;

public class Member extends Person {

	private long id;
	private boolean subscribed;
	private MemberShipPlan memberShip;

	public Member(Long id, String name,int age) {
		super(name, age);
		this.id=id;
	}
	
	public void setMemberShip(MemberShipPlan memberShip) {
		this.memberShip = memberShip;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}


	
	public long getId() {
		return id;
	}

	public boolean isSubscribed() {
		return subscribed;
	}



	public void addPlan(PlanDetails plan, int duration) {
		subscribed = true;
		this.memberShip = new MemberShipPlan(plan, duration);
	}

	@Override
	public void getDetails() {
		System.out.println("-".repeat(50));
		System.out.println("Member ID:- " + id + "\nname=" + name + "\nage=" + age);
		System.out.println();
		if (subscribed) {
			memberShip.getPlanDetails();
		} else {
			System.out.println("Not Subscribed under any plan");
		}
	}

}
