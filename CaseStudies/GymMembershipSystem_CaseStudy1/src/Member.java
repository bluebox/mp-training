
public class Member extends Person {
	private int memberId;
	private MembershipPlan plan;
	
	public Member(int memberId, String name,int age) {
		super(name,age);
		this.memberId=memberId;
		this.plan=null;
	}

	public int getMemberId() {
		return memberId;
	}


	public MembershipPlan getPlan() {
		return plan;
	}

	public void assignPlan(MembershipPlan plan) {
		this.plan = plan;
	}

	@Override
	public void showDetails() {
		System.out.println("Member ID: "+ memberId);
		System.out.println("Name: "+ getName());
		System.out.println("Age: "+ getAge());
		
		if(plan!=null) {
			
			System.out.println("Membership Plan: "+plan.getPlanName() +
					" (Duration: "+plan.getDurationMonths() +
					" months, Fee: $"+ plan.getFee() + ")");
		}else {
			System.out.println("Membership Plan: None");
		}

	}

}
