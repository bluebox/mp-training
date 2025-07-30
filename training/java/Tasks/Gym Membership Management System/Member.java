package GymMembershipManagementSystem;

public class Member extends Person{
	
	private int memberId;
    private MembershipPlan plan;
    
    public Member(String name, int age, int memberId) {
		super(name, age);
		this.memberId = memberId;
	}
    
    public Member(String name, int age, int memberId, MembershipPlan plan) {
		super(name, age);
		this.memberId = memberId;
		this.plan = plan;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public MembershipPlan getPlan() {
		return plan;
	}

	public void setPlan(MembershipPlan plan) {
		this.plan = plan;
	}
	
	
	@Override
	public void showDetails() {
		System.out.println("--------------------------------------------------");
	    System.out.println("Member ID   : " + memberId);
	    System.out.println("Name        : " + getName());
	    System.out.println("Age         : " + getAge());

	    if (plan != null) {
	        System.out.println("Plan Details: " + plan.toString());
	    } else {
	        System.out.println("Plan Details: No Membership Plan Assigned");
	    }
	    System.out.println("--------------------------------------------------");
	}

}
