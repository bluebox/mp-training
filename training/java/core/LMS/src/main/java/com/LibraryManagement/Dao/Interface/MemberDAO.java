package com.LibraryManagement.Dao.Interface;

import java.util.List;

import com.LibraryManagement.Models.Member;

public interface MemberDAO {

	public int registerMember(Member member);
	public void updateMember(Member member);
	public List<Member> getAllMembers();
	 Member getMemberById(int memberId);
}
