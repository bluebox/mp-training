package dao;

import java.util.List;

import serviceImplementation.GymServiceImplementation;
import serviceImplementation.MembershipPlanServiceImplementation;

public interface MembershipPlanDAO {
	void addAllPlans(List<MembershipPlanServiceImplementation> list);
	
	boolean selectAllAndStoreLocally(GymServiceImplementation gym);
	
	
}
