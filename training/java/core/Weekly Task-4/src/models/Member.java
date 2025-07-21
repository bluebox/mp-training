package models;

public class Member extends Person {
	private int memberId;
	private MembershipPlan membershipPlan;
	
	public Member(String name, String gender,int age) {
		super(name,gender, age);
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public MembershipPlan getMembershipPlan() {
		return membershipPlan;
	}

	public void setMembershipPlan(MembershipPlan membershipPlan) {
		this.membershipPlan = membershipPlan;
	}
	
	@Override
	public void showDetails() {
		
	}
	
	
}



