package com.SpringBoot_LMS.SpringBoot_LMS.repository;

import java.util.List;

import com.SpringBoot_LMS.SpringBoot_LMS.model.Member;



public interface MemberRepositoryInterface {
	int addMember(Member member) throws Exception;

	Member getMemberById(int id) throws Exception;

	Member updateMember(Member member) throws Exception;

	List<Member> getAllMembers() throws Exception;
}
