package com.library.service;

import com.library.dao.MemberDao;
import com.library.controller.Member;

import java.util.List;

public class MemberService {
    private final MemberDao memberDAO = new MemberDao();

    public void addMember(Member member) throws Exception {
        if (member.getEmail() == null || member.getEmail().trim().isEmpty()) {
            throw new Exception("Email is required");
        }
        memberDAO.addMember(member);
    }

    public void updateMember(Member member) throws Exception {
        if (member.getMemberId() <= 0) {
            throw new Exception("Invalid member ID");
        }
        memberDAO.updateMember(member);
    }

    public List<Member> getAllMembers() throws Exception {
        return memberDAO.getAllMembers();
    }

    public Member getMemberById(int memberId) throws Exception {
        return memberDAO.getMemberById(memberId);
    }
}