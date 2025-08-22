package com.library.service.interfaces;

import com.library.model.Member;
import java.util.List;

public interface MemberService {
    boolean registerMember(Member member) throws Exception;
    boolean modifyMember(Member member) throws Exception;
    Member fetchMemberById(int id) throws Exception;
    List<Member> fetchAllMembers() throws Exception;
}
