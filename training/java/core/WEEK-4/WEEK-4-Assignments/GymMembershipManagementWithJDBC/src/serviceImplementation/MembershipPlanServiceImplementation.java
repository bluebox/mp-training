package serviceImplementation;

import membershipplan.MembershipPlanId;
import service.MembershipPlanService;

public class MembershipPlanServiceImplementation implements MembershipPlanService {
	
	private int id;
    private String name;
    private int duration;
    private double fee;
    
    public void storePredefinedPlansLocally(GymServiceImplementation gym) {
    	gym.addPlan(new MembershipPlanServiceImplementation(MembershipPlanId.BASIC.getOneBasedOrdinal(),"Basic", 6, 999));
        gym.addPlan(new MembershipPlanServiceImplementation(MembershipPlanId.PREMIUM.getOneBasedOrdinal(),"Premium", 12, 2499));
        gym.addPlan(new MembershipPlanServiceImplementation(MembershipPlanId.GOLD.getOneBasedOrdinal(),"Gold", 18, 4499));
    }

    public MembershipPlanServiceImplementation(int id, String name, int duration, double fee) {
    	this.id=id;
        this.name = name;
        this.duration = duration;
        this.fee = fee;
    }
    
    public MembershipPlanServiceImplementation() {
    	
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
