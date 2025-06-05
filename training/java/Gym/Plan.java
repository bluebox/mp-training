package Gym;

public class Plan {
	protected String planName;
	protected int planPeriod;
	protected long planAmount;
	
	public Plan(String plan) {
		String[] planArray=plan.split(" / ");
		this.planName=planArray[0];
		this.planPeriod=planArray[1].charAt(0) == '1' ? 12 :(int)planArray[1].charAt(0)-48;
		this.planAmount=Integer.parseInt(planArray[2].replaceAll("[^0-9]", ""));
	}
	
	public String getPlanName() {
		return planName;
	}
	public int getPlanPeriod() {
		return planPeriod;
	}
	public long getPlanAmount() {
		return planAmount;
	}
}