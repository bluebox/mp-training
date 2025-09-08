package dev.kaushik.library.dao;

import java.util.List;

import dev.kaushik.library.model.Member;

public interface MemberDAO {
	int addMember(Member member);

	boolean updateMember(Member member);

	boolean deleteMember(int memberId);

	int deleteMembersInBatch(Integer... memberIds);

	List<Member> findMembers(Member criteria);

	List<Member> findMembersByIds(List<Integer> memberIds);
}