package com.library.library_management_system.services;

import java.util.List;

import com.library.library_management_system.domain.Member;

public interface MemberServiceInterface {

	Member addMember(Member member);

	Member updateMember(int id, Member member);

	List<Member> getAllMembers();

	Member getMemberById(int id);

	void deleteMember(int id);
}
