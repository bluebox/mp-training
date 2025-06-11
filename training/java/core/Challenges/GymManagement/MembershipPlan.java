package Challenges.GymManagement;

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

    public void showPlanDetails() {
        System.out.println("Plan Name: " + planName);
        System.out.println("Duration: " + durationMonths + " months");
        System.out.println("Fee: " + fee);
    }
}

