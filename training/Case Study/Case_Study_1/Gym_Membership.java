package com.Case_Study_1;

class MembershipPlan {
    private String planName;
    private int months;
    private double fee;

    public MembershipPlan(String planName, int months, double fee) {
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
