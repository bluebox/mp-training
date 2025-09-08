package dev.kaushik.library.service;

import java.util.List;

import dev.kaushik.library.model.Member;

public interface MemberService {

	Integer addMember(Member member);

	boolean updateMember(Member member);

	boolean deleteMember(Integer memberId);

	int deleteMembers(List<Integer> memberIds);

	List<Member> findMembers(Member criteria);
}