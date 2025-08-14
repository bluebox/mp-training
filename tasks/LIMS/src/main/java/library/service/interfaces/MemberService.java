package library.service.interfaces;

import java.util.List;
import java.util.Map;

import library.model.Member;

public interface MemberService {
	boolean addMember(Member member);

	Member getMemberById(int id);

	Member getMemberByEmail(String email);

	List<Member> getAllMembers();

	List<Member> findMembers(Map<String, Object> criteriaMap);

	void updateMember(Member member);

	void deleteMembers(List<Integer> memberIds);
}