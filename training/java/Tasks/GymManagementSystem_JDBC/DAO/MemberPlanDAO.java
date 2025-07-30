package GymManagementSystem.DAO;

import java.util.List;

import GymManagementSystem.models.Member;
import GymManagementSystem.models.MemberPlan;

public interface MemberPlanDAO {

	void assignPlan(MemberPlan plan);
	void updateMembership(MemberPlan plan);
	void deleteMembership(int id);
	MemberPlan getMemberPlanById(int memberId);
	List<MemberPlan> getAllMemberships();
	
	List<Member> getActiveMembers();
	void viewFullReport();
	void ExportFullReport();

}
