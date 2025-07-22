package GymManagementSystem.service;

import java.util.List;

import GymManagementSystem.models.Member;

public interface MemberService {

	void addMember(Member member);
	void updateMember(Member member);
	void deleteMember(int memberId);
	List<Member> viewMembers();
}
