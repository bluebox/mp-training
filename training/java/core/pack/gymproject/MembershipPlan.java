package gymproject;

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

    public void showDetails() {
        System.out.println("Plan: " + planName + ", Duration: " + durationMonths + " months, Fee: Rs." + fee);
    }
}