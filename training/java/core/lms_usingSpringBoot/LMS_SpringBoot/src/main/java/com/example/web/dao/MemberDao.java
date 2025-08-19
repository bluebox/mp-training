package com.example.web.dao;

import java.util.List;

import com.example.web.model.Member;

public interface MemberDao {
	int insertMember(Member newMember);
	List<Member> fetchAllMembers();
	int updateMember(Member member);
	public void addMemberLogs(Member member);
	Member getMemberById(int memberId);
}