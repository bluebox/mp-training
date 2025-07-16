package gym.membership_Management.model;

public class Member extends Person{
	private int membershipId;
	private MembershipPlan plan;
	private static int id=1;
	private MemberStatus status;
    private String removalReason;
	
	public Member(String name,int age,MembershipPlan subscribedPlan) {
		super(name,age);
		this.membershipId=id++;
		this.plan=subscribedPlan;
		this.status = MemberStatus.ACTIVE;
        this.removalReason = null;
	}
	
	public Member(int membershipId, String name, int age, MembershipPlan subscribedPlan, MemberStatus status,String removalReason) {
        super(name, age);
        this.membershipId = membershipId;
        this.plan = subscribedPlan;
        this.status = status;
        this.removalReason = removalReason;
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
	
	public MemberStatus getStatus() {
		return status;
	}
	
	public void setStatus(MemberStatus status) {
        this.status = status;
    }
	
	public String getRemovalReason() {
		return removalReason;
    }
	
	public void setRemovalReason(String removalReason) {
        this.removalReason = removalReason;
    }
	
	@Override
	public void showDetails() {
		System.out.println("ID: "+this.membershipId+" - [name: "+this.getName()+", age: "+this.getAge()+", enrolledPlan: "+plan.getNameOfPlan()+" ]");
	}
	
}
