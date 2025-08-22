package model;

public class Member extends Person {
	private String memberId;
	private MembershipPlan plan;
	public Member(String name,int age)
	{
		super(name,age);
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public MembershipPlan getPlan() {
		return plan;
	}
	public void setPlan(MembershipPlan plan) {
		this.plan = plan;
	}
}
