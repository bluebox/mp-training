package training.java.core.Gym_Management_System;

class MembershipPlan {
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

    public String getDetails() {
        return planName + " - " + durationMonths + " months - ₹" + fee;
    }
}
