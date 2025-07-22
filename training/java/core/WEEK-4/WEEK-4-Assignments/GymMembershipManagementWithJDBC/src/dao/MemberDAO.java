package dao;

import serviceImplementation.GymServiceImplementation;

public interface MemberDAO {
	int addMember(String phone, int planid, String registerdate);
	
	void updateMembershipPlan(int id,int planId);
	
	void selectAllAndStoreLocally(GymServiceImplementation gym);
}
