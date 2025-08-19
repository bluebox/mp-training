package com.lms.lms_backend.service;

import java.util.List;

import com.lms.lms_backend.model.Member;

public interface MemberService {
    void addMember(Member member);
    List<Member> getAllMembers();
    void updateMemberDetails(int id, Member newMember);
    void deleteMember(int id);
	Member getMemberById(int id);
	List<String> getAllGenders();
}
