package dao;

import java.util.List;

import model.MembershipPlan;

public interface MembershipPlanDao {
    MembershipPlan addPlan(MembershipPlan plan);
    MembershipPlan getPlanByName(String name);
    List<MembershipPlan> getAllPlans();
}