package com.librarymanagement.service;
import java.sql.SQLException;
import com.librarymanagement.dao.*;
import com.librarymanagement.exceptions.NoMemberException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.librarymanagement.model.*;

@Component
public class MemberServiceImpl implements MemberService {

    private final MemberDAO memberDAO;
    @Autowired
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
        getMemberById(member.getMemberId());
        memberDAO.updateMember(member);
    }

    @Override
    public Member getMemberById(int memberId) throws SQLException {
        if (memberId <= 0) {
            throw new IllegalArgumentException("Invalid member ID");
        }
        Member m1= memberDAO.getMemberById(memberId);
        if(m1==null)
        	throw new NoMemberException("No member found");
        return m1;
    }

    @Override
    public List<Member> getAllMembers() throws SQLException {
        return memberDAO.getAllMembers();
    }
}
