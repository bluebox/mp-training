
public class MembershipPlan {
	private String planName;
	private int duration;
	private double fee;
	
	
	public MembershipPlan(String planName, int duration, double fee) {
		this.planName = planName;
		this.duration = duration;
		this.fee = fee;
	}


	public String getPlanName() {
		return planName;
	}


	public void setPlanName(String planName) {
		this.planName = planName;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public double getFee() {
		return fee;
	}


	public void setFee(double fee) {
		this.fee = fee;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Plan Name : "+getPlanName()+" Duration of the plan : "+getDuration()+" Fee : "+getFee();
	}
	
	
}
