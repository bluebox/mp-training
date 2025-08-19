package com.lms.Dao.Interfaces;


import java.util.List;
import com.lms.Models.Member;

public interface MemberDao {
    int registerMember(Member member);
    void updateMember(Member member);
    List<Member> getAllMembers();
    Member getMemberById(int memberId);
}

