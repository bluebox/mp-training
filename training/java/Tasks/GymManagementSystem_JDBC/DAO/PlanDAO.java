package GymManagementSystem.DAO;

import java.util.List;

import GymManagementSystem.models.MembershipPlan;

public interface PlanDAO {
	
	void addPlan(MembershipPlan plan);
	void updatePlan(MembershipPlan plan);
	void deletePlan(int id);
	MembershipPlan getPlanById(int planId);
	List<MembershipPlan> getAllPlans();
}
