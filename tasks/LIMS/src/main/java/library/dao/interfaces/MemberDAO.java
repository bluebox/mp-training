package library.dao.interfaces;

import java.util.List;

import library.model.Member;

public interface MemberDAO {
	
	public boolean addMember(Member member);

	public List<Member> findMembers(Member member);
	
	public void updateMember(Member member);
	
    public void deleteMembers(List<Integer> memberIds);
	
}