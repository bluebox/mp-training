package library.service.interfaces;

import java.util.List;
import java.util.Map;

import library.model.Member;

public interface MemberService {
	String addMember(Member member);

	Member getMemberById(int id);

	Member getMemberByEmail(String email);

	List<Member> getAllMembers();

	void updateMember(Member member);

	void deleteMember(Member member);

	void deleteMembers(List<Integer> memberIds);

	List<Member> findMembers(Member criteria);
}