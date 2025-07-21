package GymPackage;


public class Member extends Person {
	private int memberId;
	private MembershipPlan plan=null;;
	
	public Member(String name, int age, int memberId) {
		super(name, age);
		this.memberId = memberId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public MembershipPlan getPlan() {
		return plan;
	}
	public void setPlan(MembershipPlan plan) {
		this.plan = plan;
	}
	  public void assignPlan(MembershipPlan plan) {
	        this.plan = plan;
	    }
	@Override
	public void showDetails() {
        System.out.println("Member ID: " + memberId);
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        if (plan != null) {
            System.out.println("Membership Plan: " + plan);
        } else {
            System.out.println("Membership Plan: Not assigned");
        }
        System.out.println("----------------------------------");
    
	}
	public String toFileString() {
	    String planName = (plan != null) ? plan.getPlanName() : "None";
	    int duration = (plan != null) ? plan.getDurationMonths() : 0;
	    double fee = (plan != null) ? plan.getFee() : 0.0;
	    return memberId + "," + getName() + "," + getAge() + "," + planName + "," + duration + "," + fee;
	}

	public static Member fromFileString(String line) {
	    String[] parts = line.split(",");
	    int id = Integer.parseInt(parts[0]);
	    String name = parts[1];
	    int age = Integer.parseInt(parts[2]);
	    String planName = parts[3];
	    int duration = Integer.parseInt(parts[4]);
	    double fee = Double.parseDouble(parts[5]);

	    Member m = new Member(name, age, id);
	    if (!planName.equals("None")) {
	        m.assignPlan(new MembershipPlan(planName, duration, fee));
	    }
	    return m;
	}

	
}
