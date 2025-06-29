
public class Member extends Person {
	private String memberId;
	private MembershipPlan membershipPlan;
	
	public Member(String memberId,String name, int age) {
		super(name, age);
		this.memberId=memberId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
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
		// TODO Auto-generated method stub
		System.out.println("MemberId : "+memberId);
		System.out.println("Name : "+getName());
		System.out.println("Age : "+getAge());
		System.out.println("-".repeat(25));
		
		if(membershipPlan!= null) {
			System.out.println("Membership Plan's name : "+membershipPlan.getPlanName());
			System.out.println("Duration of the plan : "+membershipPlan.getDuration());
			System.out.println("Cost of the plan : "+membershipPlan.getFee());
			System.out.println("-".repeat(25));
			System.out.println();
		}
		else {
			System.out.println("No plan is assigned");
		}
	}
	
	
}
