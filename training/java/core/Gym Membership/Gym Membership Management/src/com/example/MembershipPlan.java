package com.example;

public class MembershipPlan {
    private String planName;
    private int durationOfMonths;
    private double fee;

    public MembershipPlan(String planName, int durationOfMonths, double fee) {
        this.planName = planName;
        this.durationOfMonths = durationOfMonths;
        this.fee = fee;
    }

    public String getPlanName()
    { 
    	return planName; 
    }
    public int getDurationMonths() 
    { 
    	return durationOfMonths; 
    }
    public double getFee() 
    {
    	return fee; 
    }

    @Override
    public String toString() {
        return planName + " Plan - Duration: " + durationOfMonths + " months  Fee: Rs " + fee;
    }

}
