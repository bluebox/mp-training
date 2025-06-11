package gymtask1;

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
    
    public int getDurationMonths() {
        return durationMonths;
    }
    
    public double getFee() {
        return fee;
    }
    
    
    public void setPlanName(String planName) {
        this.planName = planName;
    }
    
    public void setDurationMonths(int durationMonths) {
        this.durationMonths = durationMonths;
    }
    
    public void setFee(double fee) {
        this.fee = fee;
    }
    
    
    public void displayPlanDetails() {
        System.out.println("Plan: " + planName + ", Duration: " + durationMonths + " months, Fee: $" + fee);
    }
    
    @Override
    public String toString() {
        return planName + " (" + durationMonths + " months, $" + fee + ")";
    }
}
