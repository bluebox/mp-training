package com.library.dao.interfaces;

import com.library.model.Member;
import java.util.List;

public interface MemberDao {
    boolean addMember(Member member);
    boolean updateMember(Member member);
   
    List<Member> getAllMembers();
    Member getMemberById(int memberId);
}
