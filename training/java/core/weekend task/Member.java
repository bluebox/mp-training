package GymManagement;

public class Member extends Person {
	private static int nextMemberId=1;
	private final int memberId;
	private MembershipPlan assignedPlan;
	public Member(String name,int age)
	{
		super(name,age);
		this.memberId=nextMemberId++;
		this.assignedPlan=null;
		
	}
	public int getMemberId()
	{
		return memberId;
	}
	public MembershipPlan getAssignedPlan()
	{
		return assignedPlan;
	}
	public void setAssignerPlan(MembershipPlan assignedPlan) {
		this.assignedPlan=assignedPlan;
	}
	public void showDetails() {
		System.out.println();
		System.out.println("member id : "+memberId);
		System.out.println("Name : " +getName());
		System.out.println("Age : "+getAge());
		if(assignedPlan !=null)
		{
			System.out.println("Membership plan: "+assignedPlan.getPlanName()+" Duration : "+assignedPlan.getDurationMonths()+"months, Fee: $ "+assignedPlan.getFee());
		}
		else
		{
			System.out.println("MembershipPlan: Not Asiigned Till Now");
		}
		

	}

}
