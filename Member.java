public class Member extends Person{
	private String memberId;
	private MembershipPlan plan;
	public Member(String memberId,String name,int age)
	{
		super(name,age);
		this.memberId=memberId;
	}
	public void assignPlan(MembershipPlan plan)
	{
		this.plan=plan;
	}
	public void showDetails()
	{
		System.out.println("ID: " + memberId + ", Name: " + name + ", Age: " + age);
		if(plan!=null)
		{
			System.out.println("Membership Plan is :");
			plan.showPlanDetails();
		}
		else
		{
			
	            System.out.println("No Membership Plan Assigned for the "+name);
		}
	}
	public String getMemberId()
	{
		return memberId;
	}
}