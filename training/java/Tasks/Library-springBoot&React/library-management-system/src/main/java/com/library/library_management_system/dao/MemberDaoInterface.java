package com.library.library_management_system.dao;

import java.util.List;

import com.library.library_management_system.domain.Member;

public interface MemberDaoInterface {
	
	Member insertMember(Member member);

    Member updateMember(Member member);

    int deleteMember(int memberId);

    Member getMemberById(int id);

    List<Member> getAllMembers();

}
