package com.LMS.LibMS.service.interfaces;

import java.util.List;

import com.LMS.LibMS.model.Member;

public interface MemberService {
	void addMember(Member member) throws Exception;

	List<Member> getAllMembers();

	Member getMemberById(Integer id);

	boolean updateMember(Member member) throws Exception;

	boolean deleteMembersById(List<Integer> memberIds) throws Exception;
}