package com.library.service;

import com.library.dao.MemberDAO;
import com.library.domain.Member;

import java.util.List;

public class MemberService {

    private MemberDAO memberDAO;

    // Default constructor
    public MemberService() {
        this.memberDAO = new MemberDAO();
    }

    // Constructor for testing (injecting fake/mock DAO)
    public MemberService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    // Protected method to be overridden in test subclass if needed
    public MemberDAO getMemberDAO() {
        return memberDAO;
    }

    public boolean addMember(Member member) {
        return memberDAO.addMember(member);
    }

    public List<Member> viewAllMembers() {
        return memberDAO.getAllMembers();
    }

    public boolean updateMember(Member member) {
        return memberDAO.updateMember(member);
    }
}
