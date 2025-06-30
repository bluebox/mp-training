package gym.membership_Management;

public class Member extends Person{
	private int membershipId;
	private MembershipPlan plan;
	private static int id=1;
	
	public Member(String name,int age,MembershipPlan p) {
		super(name,age);
		this.membershipId=id++;
		this.plan=p;
	}
	
	public int getMembershipId() {
		return membershipId;
	}

	public MembershipPlan getPlan() {
		return plan;
	}

	public void setPlan(MembershipPlan plan) {
		this.plan = plan;
	}
	
	@Override
	public void showDetails() {
		System.out.println("ID: "+this.membershipId+" - [name: "+this.getName()+", age: "+this.getAge()+", enrolledPlan: "+plan.getNameOfPlan()+" ]");
	}
	
}
