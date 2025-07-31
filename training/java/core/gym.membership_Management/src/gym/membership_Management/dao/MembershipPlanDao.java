package gym.membership_Management.dao;

import gym.membership_Management.model.MembershipPlan;
import java.util.List;

public interface MembershipPlanDao {
    MembershipPlan addPlan(MembershipPlan plan);
    MembershipPlan getPlanByName(String name);
    List<MembershipPlan> getAllPlans();
    
    void removePlanByName(String name);
}