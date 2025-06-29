
public class Member extends Person {
	
private String memberId;
private MembershipPlan plan;

public Member(String memberId,String name,int age)
{
	super(name,age);
	this.memberId=memberId;
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

public void assignPlan(MembershipPlan plan)
{
	this.plan=plan;
}

public void showDetails()
{
	System.out.println("Member ID: "+memberId);
	System.out.println("Name of  the Member: "+getName());
	System.out.println("Age of the Memeber:"+getAge());
	if(plan!=null)
		plan.showPlanDetails();
	else
		System.out.println("No Plan Assigned To This Person ");
}
}
