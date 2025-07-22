package GymManagementSystem.service;

import java.util.List;

import GymManagementSystem.models.Member;
import GymManagementSystem.models.MemberPlan;

public interface MemberPlanService {

	void mapMemberToPlan(MemberPlan mapping);
	void updateMembership(MemberPlan plan);
	void deleteMembership(int memberId);
	List<MemberPlan> viewMemberships();
	List<Member> viewActiveMembers();
	void getReport();
}
