package com.LibraryManagement.dao;

import java.util.ArrayList;

import com.LibraryManagement.utilites.pojos.Member;

public interface MemberDao {
	public boolean addMember(Member member);
	public boolean updateMemberDetails(int memberId,String name,String email, Long mobile) throws Exception;
	public ArrayList<Member>  viewAllMembers() throws Exception;
	public boolean verifyMember(int memberId,String name,String email, Long mobile) throws Exception;
	public boolean verifyMember(Member member) throws Exception;
	public ArrayList<Member> viewAllMembersLog() throws Exception;
}
