package library.dao.interfaces;

import java.util.List;

import library.model.Member;

public interface MemberDAO {
	public String addMember(Member member);

	public void updateMember(Member member);

	public void deleteMember(Member member);
	
    public void deleteMembersInBatch(List<Integer> memberIds);
	
	public List<Member> findMembers(Member member);
}