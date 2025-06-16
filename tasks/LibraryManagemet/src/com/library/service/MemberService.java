package com.library.service;

import com.library.dao.MemberDAO;
import com.library.domain.Member;
import com.library.utilities.MemberValidation;

import java.util.List;

public class MemberService {

    private MemberDAO memberDAO;

    public MemberService() {
        this.memberDAO = new MemberDAO();
    }

    public MemberService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public MemberDAO getMemberDAO() {
        return memberDAO;
    }

    public boolean addMember(Member member) {
        if (!MemberValidation.isValidMember(member)) {
            throw new IllegalArgumentException("Invalid member data");
        }
        return memberDAO.addMember(member);
    }

 

    public List<Member> viewAllMembers() {
        return memberDAO.getAllMembers();
    }

    public boolean updateMember(Member member) {
        return memberDAO.updateMember(member);
    }
}
