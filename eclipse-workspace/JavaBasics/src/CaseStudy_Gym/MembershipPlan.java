package CaseStudy_Gym;

public class MembershipPlan {
	private String MembershipPlan;
    private int duration;
    private double fee;

    public MembershipPlan(String MembershipPlan, int duration, double fee) {
        this.MembershipPlan = MembershipPlan;
        this.duration = duration;
        this.fee = fee;
    }

    public String getMembershipPlan() {
        return MembershipPlan;
    }

    public int getduration() {
        return duration;
    }

    public double getFee() {
        return fee;
    }

    @Override
    public String toString() {
        return MembershipPlan + " MembershipPlan - " + duration + " months, Fee: $" + fee;
    }
}
