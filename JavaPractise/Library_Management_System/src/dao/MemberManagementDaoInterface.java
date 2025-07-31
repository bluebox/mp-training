package dao;

import java.util.List;

import domain.Member;

public interface MemberManagementDaoInterface {
	boolean registerNewMember(Member m) throws Exception;
	boolean updaterMemberDetails(Member m) throws Exception;
	List<Member> viewAllMembers()throws Exception;
}