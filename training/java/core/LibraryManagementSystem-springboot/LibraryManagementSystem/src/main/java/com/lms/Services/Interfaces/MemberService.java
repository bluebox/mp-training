package com.lms.Services.Interfaces;



import com.lms.Models.Member;
import java.util.List;

public interface MemberService {

    int registerMember(Member member);

    void updateMember(Member member);

    List<Member> getAllMembers();

    Member fetchMemberById(int id) throws Exception;
}
