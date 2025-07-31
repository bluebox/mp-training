package service;

import java.util.List;
import domain.Member;

public interface MemberManagementServiceInterface {
	boolean registerMember(Member member) throws Exception;
	boolean updateMember(Member member) throws Exception;
	List<Member> getAllMembers() throws Exception;
}