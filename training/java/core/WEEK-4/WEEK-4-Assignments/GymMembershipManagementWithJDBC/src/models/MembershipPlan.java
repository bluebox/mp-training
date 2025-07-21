package models;

public class MembershipPlan {
	
	private int id;
    private String name;
    private int duration;
    private double fee;
    
    public static void storePredefinedPlansLocally(Gym gym) {
    	gym.addPlan(new MembershipPlan(MembershipPlanId.BASIC.getOneBasedOrdinal(),"Basic", 6, 999));
        gym.addPlan(new MembershipPlan(MembershipPlanId.PREMIUM.getOneBasedOrdinal(),"Premium", 12, 2499));
        gym.addPlan(new MembershipPlan(MembershipPlanId.GOLD.getOneBasedOrdinal(),"Gold", 18, 4499));
    }

    public MembershipPlan(int id, String name, int duration, double fee) {
    	this.id=id;
        this.name = name;
        this.duration = duration;
        this.fee = fee;
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public double getFee() {
        return fee;
    }

    @Override
    public String toString() {
        return name + " Plan (" + duration + " months, ₹" + fee + ")";
    }
}
