package com.libraryManagement.dao;

import java.util.List;

import com.libraryManagement.utility.pojos.Member;

public interface MemberDao {
	public boolean addMember(Member mem);

	boolean updateMember(Member mem);

	boolean addMemberLog(Member mem);

	List<Member> allMember();
}
