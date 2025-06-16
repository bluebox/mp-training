package service;

import dao.MemberDAO;
import exception.InvalidInputException;
import model.Member;

import java.util.List;

public class MemberService {

    private MemberDAO memberDAO;

    public MemberService() {
        this.memberDAO = new MemberDAO();
    }

    public void addMember(Member member) throws Exception {
        if (member.getName() == null || member.getName().trim().isEmpty()) {
            throw new InvalidInputException("Name cannot be empty");
        }
        if (member.getEmail() == null || member.getEmail().trim().isEmpty()) {
            throw new InvalidInputException("Email cannot be empty");
        }
        // Add more validations as needed
        memberDAO.addMember(member);
    }

    public List<Member> getAllMembers() throws Exception {
        return memberDAO.getAllMembers();
    }
}
