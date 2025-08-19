package com.lms.Services.Implementation;




import com.lms.Exceptions.MemberDaoException;
import com.lms.Models.Member;
import com.lms.Repository.MemberRepo;
import com.lms.Services.Interfaces.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImplementation implements MemberService {

    private final MemberRepo memberDAO;

    @Autowired
    public MemberServiceImplementation(MemberRepo memberDAO) {
        this.memberDAO = memberDAO;
    }

    @Override
    public int registerMember(Member member) {
        if (member == null || member.getName() == null || member.getEmail() == null ||
            member.getGender() == null || member.getAddress() == null) {
            throw new MemberDaoException("Invalid: Member data can't be null");
        }
        return memberDAO.registerMember(member);
    }

    @Override
    public void updateMember(Member member) {
        if (member == null || member.getMemberId() <= 0) {
            throw new MemberDaoException("Invalid member ID for update.");
        }
        memberDAO.updateMember(member);
    }

    @Override
    public List<Member> getAllMembers() {
        return memberDAO.getAllMembers();
    }

    @Override
    public Member fetchMemberById(int id)  {
        if (id <= 0)
            throw new RuntimeException("Invalid Member ID");

        Member member = memberDAO.getMemberById(id);
        if (member == null)
            throw new RuntimeException("Member not found");

        return member;
    }
}

