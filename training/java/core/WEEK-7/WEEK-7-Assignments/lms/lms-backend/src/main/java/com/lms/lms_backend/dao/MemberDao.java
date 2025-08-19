package com.lms.lms_backend.dao;

import java.util.List;

import com.lms.lms_backend.model.Member;

public interface MemberDao {
    int insertMemberLog(Member member);
    int addMember(Member member);
    int updateMemberDetails(Member oldMember, Member newMember);
    int deleteMember(Member member);
    List<Member> selectAllMembers();
    Member selectMemberById(int id);
	List<String> selectAllGenders();
}
