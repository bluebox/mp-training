package gymManagementSystem;

public class MemberShipPlan {
	
	private String nameOfThePlan;
	private int durationInMonth;
	private int fee;
	
	public MemberShipPlan(String nameOfThePlan, int durationInMonth, int fee) {
		this.nameOfThePlan = nameOfThePlan;
		this.durationInMonth = durationInMonth;
		this.fee = fee;
	}

	public String getNameOfThePlan() {
		return nameOfThePlan;
	}

	public void setNameOfThePlan(String nameOfThePlan) {
		this.nameOfThePlan = nameOfThePlan;
	}

	public int getDurationInMonth() {
		return durationInMonth;
	}

	public void setDurationInMonth(int durationInMonth) {
		this.durationInMonth = durationInMonth;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	@Override
	public String toString() {
		return "NameOfThePlan= " + nameOfThePlan + ", DurationInMonth= " + durationInMonth + ", Fee= "
				+ fee;
	}
	
	
	
}
