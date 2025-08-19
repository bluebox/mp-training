package com.example.library.dao;

import java.util.List;

import com.example.library.domain.Member;

public interface MemberDao {

	int memberlog(int memberId);

	Member findById(int id);

	List<Member> findAllMembers();

	int deleteMember(int id);

	int updateMember(Member member);

	int addMember(Member member);

}
