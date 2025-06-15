package service;

import dao.MemberDAO;
import model.Member;

public class MemberService {

    private final MemberDAO memberDAO = new MemberDAO();

    public void updateMember(Member member) throws Exception {
        if (member.getMemberId() <= 0) throw new IllegalArgumentException("Invalid Member ID.");
        if (member.getName() == null || member.getName().isBlank()) throw new IllegalArgumentException("Name is required.");
        if (member.getEmail() == null || member.getEmail().isBlank()) throw new IllegalArgumentException("Email is required.");
        if (!(member.getGender() == 'M' || member.getGender() == 'F')) throw new IllegalArgumentException("Gender must be M or F.");
        if (member.getAddress() == null || member.getAddress().isBlank()) throw new IllegalArgumentException("Address is required.");

        memberDAO.updateMember(member);
    }
}

