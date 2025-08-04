package DAO;

import java.util.List;

import Domain.Member;

public interface MemberInterface {

	int addMember(Member member) throws Exception;

	Member getMemberById(int id) throws Exception;

	Member updateMember(Member member) throws Exception;

	List<Member> getAllMembers() throws Exception;


}
