package CaseStudy1;

class Member extends Person{
	private long memberId;
	private MembershipPlan plan;
	private String name;
	private int age;
	public Member(String name, int age, long memberId, MembershipPlan plan){
		this.name=name;
		this.age=age;
		this.setMemberId(memberId);
		this.setPlan(plan);
	}

	
	void setName(String name) {
		this.name=name;
		
	}
	
	void setAge(int age) {
		this.age=age;
	}
	String getName() {
		return name;
	}
	
	int getAge() {
		return age;
	}

	
	public MembershipPlan getPlan() {
		return plan;
	}

	
	public void setPlan(MembershipPlan plan) {
		this.plan = plan;
	}

	
	public long getMemberId() {
		return memberId;
	}

	
	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}
	public void showDetails( ) {
		System.out.println( "Person with name " + this.name + " of age "+ this.age+ " with member id "+this.memberId  +" choosed"
+ " plan containing "+ this.plan.getPlanName() +" for "+ this.plan.getDurationMonths()+" with monthly fee of "+this.plan.getFee());
	}
	

}
