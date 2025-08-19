package dev.tulasidhar.lms.DAO;

import java.util.List;
import dev.tulasidhar.lms.model.Member;

public interface MemberDao {
	int insertMember(Member newMember);
	List<Member> fetchAllMembers();
	int updateMember(Member member);
	public void addMemberLogs(Member member);
	Member getMemberById(int memberId);
}