package gym;

public class Member extends Person {
	private String plan;
	private int memberId;
	
	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public Member(String name, int age, String plan, int memberId) {
		super(name, age);
		this.plan = plan;
		this.memberId = memberId;
	}
	
	
}
