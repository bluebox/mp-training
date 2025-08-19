package dev.tulasidhar.lms.service;

import java.util.List;

import dev.tulasidhar.lms.Exceptions.ValidationException;
import dev.tulasidhar.lms.model.Member;

public interface MemberService {
	
	void addNewMember(Member member) throws ValidationException;
	List<Member> getAllMembers();
	void updateMember(Member member) throws ValidationException;
	String getMemberNameById(int id);
	Member getMemberById(int memberId);
	Member getMemberByEmail(String email);
}
