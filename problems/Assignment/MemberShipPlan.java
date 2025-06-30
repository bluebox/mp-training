package Assignment;

public class MemberShipPlan{

	private String name;
	private int fee;
	private int duration;
	
	public MemberShipPlan() {
		
	}
	
	public MemberShipPlan(String name, int fee, int duration) {
		this.name = name;
		this.fee = fee;
		this.duration = duration;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	
	
}

