
public class MembershipPlan  {
	private String planName;
	private int durationInMonths;
	private double feePerMonth;
	
	public MembershipPlan(String planName,int durationInMonths,double feePerMonth)
	{
		this.planName=planName;
		this.durationInMonths=durationInMonths;
		this.feePerMonth=feePerMonth;
	}
	
	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public int getDurationInMonths() {
		return durationInMonths;
	}

	public void setDurationInMonths(int durationInMonths) {
		this.durationInMonths = durationInMonths;
	}

	public double getFee() {
		return feePerMonth;
	}

	public void setFee(double feePerMonth) {
		this.feePerMonth = feePerMonth;
	}

	public void showPlanDetails()
	{
		System.out.println("Plan: "+ planName +", Duration: "+durationInMonths+" Months,FeePerMonth: Rs."+feePerMonth);
	

	}
}
