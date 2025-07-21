package dao;

import java.util.List;

import models.Member;

public interface MemberDao {

	void addMember(Member member);

	Member getMemberById(int id);

	List<Member> getAllMembers();

	void updateMember(Member member);

	void deleteMember(int id);

}
