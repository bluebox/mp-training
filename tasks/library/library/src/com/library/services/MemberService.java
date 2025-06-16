package com.library.services;

import com.library.dao.MemberDAO;
import com.library.domain.Member;


public class MemberService {
    MemberDAO memberDAO = new MemberDAO();
    public boolean updateMember(Member member) {
        return memberDAO.updateMember(member);
    }
}