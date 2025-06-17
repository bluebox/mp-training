package com.medplus.dao;

import com.medplus.model.Member;
import java.util.List;

public interface MemberDAO {
    void addMember(Member member) throws Exception;
    List<Member> getAllMembers() throws Exception;
    void updateMember(Member member) throws Exception;
}