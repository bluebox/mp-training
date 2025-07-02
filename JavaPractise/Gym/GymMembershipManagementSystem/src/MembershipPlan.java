public class MembershipPlan {
	private String planName;
	private int durationMonths;
	private double fee;
	MembershipPlan(String planName,int durationMonths,double fee)
	{
		this.planName=planName;
		this.durationMonths=durationMonths;
		this.fee=fee;
	}
	public String getplanName()
	{
		return planName;
	}
	public int getDuration()
	{
		return durationMonths;
	}
	public double getFee()
	{
		return fee;
	}
 	public void showPlanDetails() {
		 System.out.println("Plan: " + planName + ", Duration: " + durationMonths + " months, Fee: ₹" + fee);
	}
}