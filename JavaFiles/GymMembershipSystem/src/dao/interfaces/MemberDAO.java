package dao.interfaces;

import java.util.List;

import model.Member;

public interface MemberDAO {
	void addMember(Member member);

	Member getMemberById(int memberId);

	List<Member> getAllMembers();

	void assignPlanToMember(int memberId, int planId);

	boolean memberExists(int memberId);
}
