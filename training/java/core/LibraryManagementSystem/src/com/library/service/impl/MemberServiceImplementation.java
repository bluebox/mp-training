package com.library.service.impl;

import com.library.dao.interfaces.MemberDao;
import com.library.dao.impl.MemberDaoImplementation;
import com.library.model.Member;
import com.library.service.interfaces.MemberService;

import java.util.List;

public class MemberServiceImplementation implements MemberService {

    private final MemberDao dao = new MemberDaoImplementation();

    @Override
    public boolean registerMember(Member member) throws Exception {
        validateMember(member);
        return dao.addMember(member);
    }

    @Override
    public boolean modifyMember(Member member) throws Exception {
        if (member.getMemberId() <= 0)
            throw new Exception("Invalid Member ID");

        validateMember(member);
        return dao.updateMember(member);
    }

    @Override
    public Member fetchMemberById(int id) throws Exception {
        if (id <= 0)
            throw new Exception("Invalid Member ID");

        Member member = dao.getMemberById(id);
        if (member == null)
            throw new Exception("Member not found");

        return member;
    }

    @Override
    public List<Member> fetchAllMembers() throws Exception {
        return dao.getAllMembers();
    }

    private void validateMember(Member member) throws Exception {
        if (member.getName() == null || member.getName().trim().isEmpty())
            throw new Exception("Name cannot be empty");

        if (member.getEmail() == null || !member.getEmail().matches("^\\S+@\\S+\\.\\S+$"))
            throw new Exception("Invalid email format");

        if (member.getMobile() < 1000000000L || member.getMobile() > 9999999999L)
            throw new Exception("Invalid mobile number");

        if (!"M".equalsIgnoreCase(member.getGender()) && !"F".equalsIgnoreCase(member.getGender()))
            throw new Exception("Gender must be 'M' or 'F'");

        if (member.getAddress() == null || member.getAddress().trim().length() < 5)
            throw new Exception("Address must be at least 5 characters");
    }
    public boolean memberExists(int memberId) {
        return dao.getMemberById(memberId) != null;
    }
}
