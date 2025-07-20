package model;

public class MembershipPlan {
    private int id;
    private String planName;
    private int durationMonths;
    private double fee;

    public MembershipPlan(int id, String planName, int durationMonths, double fee) {
        this.id = id;
        this.planName = planName;
        this.durationMonths = durationMonths;
        this.fee = fee;
    }

    public int getId() {
        return id;
    }

    public void showDetails() {
        System.out.println("Plan: " + planName + " | Duration: " + durationMonths + " months | Fee: ₹" + fee);
    }
}
