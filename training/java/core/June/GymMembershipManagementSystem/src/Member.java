
public class Member extends Person {
	
	private String memberId;
	private MembershipPlan plan;
	
	public Member(String memberId,String name, int age) {
		super(name, age);
		this.memberId = memberId;
		this.plan = null;
	}

	public Member(String name, int age, String memberId, MembershipPlan plan) {
		super(name, age);
		this.memberId = memberId;
		this.plan = plan;
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
	
	public void showDetails () {
		
		System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        if (plan != null) {
            System.out.println("Membership Plan: " + plan.toString());
        } else {
            System.out.println("Membership Plan: Not Assigned");
        }
	}
}
