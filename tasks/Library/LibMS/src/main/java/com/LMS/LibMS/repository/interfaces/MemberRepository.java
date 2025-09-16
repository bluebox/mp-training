package com.LMS.LibMS.repository.interfaces;

import java.util.List;

import com.LMS.LibMS.model.Member;

public interface MemberRepository {
	void addMember(Member member);

	List<Member> findMembersById(List<Integer> ids);

	List<Member> getAllMembers();

	boolean updateMember(Member member) throws Exception;

	boolean deleteMembersById(List<Integer> memberIds) throws Exception;

	boolean logMember(Member member);
}