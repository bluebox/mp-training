package com.Class;

public class Gym_MembershipPlan {
    private String planName;
    private int months;
    private double fee;

    public Gym_MembershipPlan(String planName, int months, double fee) {
        this.planName = planName;
        this.months = months;
        this.fee = fee;
    }

    public String getPlanName() { return planName; }
    public int getMonths() { return months; }
    public double getFee() { return fee; }

    public String toString() {
        return planName + " | " + months + " months | $" + fee;
    }
}