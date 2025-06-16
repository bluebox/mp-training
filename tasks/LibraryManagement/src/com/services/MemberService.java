package com.services;

import com.DAO.MemberDAO;
import com.models.Member;

public class MemberService {
    private final MemberDAO dao = new MemberDAO();

    public boolean addMember(String name, String email, int mobile, char gender, String address) {
        Member member = new Member(0,name, email, mobile, gender, address);
        
        return dao.save(member);
    }
}
