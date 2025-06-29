package Default;

public class MembershipPlan {
    private String planName;
    private int durationMonths;
    private double fee;

    public MembershipPlan(String planName, int durationMonths, double fee) {
        this.planName = planName;
        this.durationMonths = durationMonths;
        this.fee = fee;
    }

    public String getPlanName() {
    	
        return planName;
    }
    

    public void showDetails() {
        System.out.println("Plan of a Member: " + planName);
        System.out.println("Duration of Plan: " + durationMonths + " months");
        System.out.println("Fee for the plan: $" + fee);
    }
}

