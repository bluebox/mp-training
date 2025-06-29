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
	
	public void assignPlan(MembershipPlan plan) { 
		this.plan = plan; 
	}
	
	public void showDetails() {
        System.out.print("ID: " + memberId + ", Name: " + getName() + ", Age: " + getAge() + ",");
        if (plan != null) System.out.println(" " + plan.toString());
        else System.out.println(" No Membership Plan Assigned");
	}

}
