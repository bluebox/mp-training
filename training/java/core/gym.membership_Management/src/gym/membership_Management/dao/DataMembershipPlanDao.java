package gym.membership_Management.dao;

import gym.membership_Management.model.MembershipPlan;
import java.util.ArrayList;
import java.util.List;

public class DataMembershipPlanDao implements MembershipPlanDao {
    private List<MembershipPlan> plans = new ArrayList<>();

    public DataMembershipPlanDao() {
        plans.add(new MembershipPlan("Basic", 30, 999));
        plans.add(new MembershipPlan("Gold", 90, 2499));
        plans.add(new MembershipPlan("Premium", 180, 4999));
    }

    @Override
    public MembershipPlan addPlan(MembershipPlan plan) {
        plans.add(plan);
        return plan;
    }

    @Override
    public MembershipPlan getPlanByName(String name) {
        return plans.stream()
                    .filter(plan -> plan.getNameOfPlan().equalsIgnoreCase(name))
                    .findFirst()
                    .orElse(null);
    }

    @Override
    public List<MembershipPlan> getAllPlans() {
        return new ArrayList<>(plans);
    }
}
