package com.gym.models;

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
	
	public void setMemberId(int id) {
		this.memberId = id;
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
	
	public Member(String name, int age,int height, int weight) {
		this(0,name,age,height,weight,null,"NA");
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


	@Override
	public void showDetails() {
	    String planName = (getMembershipPlan() == null) ? "No Plan Assigned" : getMembershipPlan().planName;
	    String joinInfo = joinDate.equals("NA") ? "" : joinDate;
	    
	    System.out.printf(
	        "%-6d %-20s %-4d %-6d %-7d %-20s %-15s%n",
	        getMemberId(),
	        getMemberName(),
	        getMemberAge(),
	        getMemberHeight(),
	        getMemberWeight(),
	        planName,
	        joinInfo
	    );
	}

	
	
	
}
