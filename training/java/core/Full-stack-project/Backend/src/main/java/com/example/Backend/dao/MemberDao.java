package com.example.Backend.dao;

import java.util.List;

import com.example.Backend.domain.Member;

public interface MemberDao {
	int addMember(Member member);

	int updateMember(Member member);

	List<Member> findAllMembers();

	Member findById(int id);

	int memberlog(int memberId);

}
