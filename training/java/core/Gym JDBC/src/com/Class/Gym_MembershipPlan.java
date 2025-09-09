package com.Class;

public class Gym_MembershipPlan {

    public static String getPlan(int option) {
        switch (option) {
            case 1:
                return "Basic - 1 month - $29.99";
            case 2:
                return "Standard - 2 months - $49.99";
            case 3:
                return "Premium - 3 months - $79.99";
            default:
                return "No Plan";
        }
    }

    public static int getPlanLevel(String planName) {
        if (planName.contains("Basic")) return 1;
        if (planName.contains("Standard")) return 2;
        if (planName.contains("Premium")) return 3;
        return 0; 
    }
}
