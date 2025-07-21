package model;

import java.util.ArrayList;
import java.util.List;

public class MembershipPlan {
    private String planName;
    private int durationMonths;
    private double price;

    public MembershipPlan(String planName, int durationMonths, double price) {
        this.planName = planName;
        this.durationMonths = durationMonths;
        this.price = price;
    }

    public String getPlanName() { 
    	return planName; 
    	}
    public int getDurationMonths() {
    	return durationMonths; 
    	}
    public double getPrice() { 
    	return price; 
    	}

    public static List<MembershipPlan> getAllPlans() {
        List<MembershipPlan> plans = new ArrayList<>();
        plans.add(new MembershipPlan("Basic", 1, 999.00));
        plans.add(new MembershipPlan("Standard", 3, 2499.00));
        plans.add(new MembershipPlan("Premium", 6, 4499.00));
        plans.add(new MembershipPlan("Annual", 12, 7999.00));
        return plans;
    }
}