package gymManagement;

public enum MembershipPlan {
	BASIC("Basic", 6, 3000),
    STANDARD("Standard", 12, 5000),
    PREMIUM("Premium", 24, 8000);

    private final String planName;
    private final int durationMonths;
    private final double fee;

    MembershipPlan(String planName, int durationMonths, double fee) {
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

    public void displayPlanDetails() {
        System.out.println("Membership Plan: " + planName);
        System.out.println("Duration: " + durationMonths + " months");
        System.out.println("Fee: " + fee);
    }
}
