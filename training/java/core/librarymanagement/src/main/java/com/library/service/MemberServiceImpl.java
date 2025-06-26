package com.library.service;

import java.sql.SQLException;
import java.util.List;
import com.library.dao.MemberDAO;
import com.library.model.Member;

public class MemberServiceImpl implements MemberService {

    private final MemberDAO memberDAO;
    public MemberServiceImpl(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    @Override
    public void addMember(Member member) throws SQLException {
        if (member == null || member.getName() == null || member.getEmail() == null) {
            throw new IllegalArgumentException("Invalid member data");
        }
        memberDAO.addMember(member);
    }

    @Override
    public void updateMember(Member member) throws SQLException {
        if (member == null || member.getMemberId() <= 0) {
            throw new IllegalArgumentException("Invalid member for update");
        }
        memberDAO.updateMember(member);
    }

    @Override
    public Member getMemberById(int memberId) throws SQLException {
        if (memberId <= 0) {
            throw new IllegalArgumentException("Invalid member ID");
        }
        return memberDAO.getMemberById(memberId);
    }

    @Override
    public List<Member> getAllMembers() throws SQLException {
        return memberDAO.getAllMembers();
    }
}
