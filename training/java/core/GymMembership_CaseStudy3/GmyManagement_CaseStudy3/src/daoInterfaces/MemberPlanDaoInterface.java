package daoInterfaces;

import java.sql.SQLException;
import java.util.List;

import model.MembershipPlan;

public interface MemberPlanDaoInterface {
	 public void assignPlan(String memberId, String planId, String planName) throws SQLException;
	 public List<MembershipPlan> getPlansForMember(String memberId) throws SQLException;
	 public void updatePlan(String memberId,String newPlanId,String newPlanname) throws SQLException;
}
