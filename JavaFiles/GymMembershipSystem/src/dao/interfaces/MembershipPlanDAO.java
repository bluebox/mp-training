package dao.interfaces;

import java.util.List;

import model.MembershipPlan;

public interface MembershipPlanDAO {
	void addPlan(MembershipPlan plan);

	MembershipPlan getPlanByName(String planName);

	List<MembershipPlan> getAllPlans();

	boolean planExists(String planName);

	int getPlanIdByName(String planName);

}
