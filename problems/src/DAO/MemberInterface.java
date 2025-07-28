package DAO;

import java.util.List;

import Domain.Member;

public interface MemberInterface {

	void addMember(Member member) throws Exception;

	Member getMemberById(int id) throws Exception;

	Member updateMember(Member member) throws Exception;

	List<Member> getAllMembers() throws Exception;

	void deleteMember(int memberId) throws Exception;

}
