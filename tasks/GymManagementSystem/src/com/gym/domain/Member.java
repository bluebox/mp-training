package com.gym.domain;

public class Member extends Person{
	
	private static long idGenerator=100;
	private long memId;
	public boolean isSubscribed;
	private MemberShipPlan memberShip;
	

	public void setMemberShip(MemberShipPlan memberShip) {
		this.memberShip = memberShip;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	public void setAge(int age)
	{
		this.age=age;
	}

	public Member(String name, int age) {
		super(name, age);
		memId=idGenerator++;
		this.isSubscribed=false;
	}
	
	public long getMemberId() {
		return memId;
	}
	
	public void addPlan(PlanDetails plan,int duration)
	{
		isSubscribed=true;
		this.memberShip= new MemberShipPlan(plan,duration);
	}
	@Override
	public void getDetails() {
		System.out.println("-".repeat(50));
		System.out.println( "Member ID:- " + memId
				+ "\nname=" + name + "\nage=" + age);
		System.out.println();
		if(isSubscribed)
		{
			memberShip.getPlanDetails();	
		}
		else
		{
			System.out.println("Not Subscribed under any plan");
		}
	}

	
	
	
	
	


}
