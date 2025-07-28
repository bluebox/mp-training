package service;

import java.util.List;
import domain.Member;

public interface MemberManagementServiceInterface {
	boolean registerMember(Member member) throws Exception;
	boolean updateMember(int memberId, Member member) throws Exception;
	List<Member> getAllMembers();
}