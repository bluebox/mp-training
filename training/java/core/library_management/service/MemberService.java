package service;

import java.sql.SQLException;
import java.util.List;

import DAO.MemberDAO;
import pojo.Member;
import service.exceptions.DatabaseException;
import service.exceptions.InvalidInputException;

public class MemberService {
    private MemberDAO memberDAO;

    public MemberService() {
        this.memberDAO = new MemberDAO();
    }

    public void addMember(Member member) throws InvalidInputException, DatabaseException {
        if (member == null || member.getName() == null || member.getEmail() == null ||
            member.getMobile() <= 0 || member.getGender() == null ||
            member.getAddress() == null){
            throw new InvalidInputException("Member details cannot be null or empty/invalid.");
        }
        try {
            memberDAO.addMember(member);
        } catch (SQLException e) {
            throw new DatabaseException("Error adding member to the database.", e);
        }
    }

    public void updateMember(Member member) throws InvalidInputException, DatabaseException {
        if (member == null || member.getMemberId() <= 0 ||
            member.getName() == null ||member.getEmail() == null ||
            member.getMobile() <= 0 || member.getGender() == null ||
            member.getAddress() == null) {
            throw new InvalidInputException("Member details for update cannot be null or empty/invalid.");
        }
        try {
            memberDAO.updateMember(member);
        } catch (SQLException e) {
            throw new DatabaseException("Error updating member details in the database.", e);
        }
    }

    public List<Member> getAllMembers() throws DatabaseException {
        try {
            return memberDAO.getAllMembers();
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving all members from the database.", e);
        }
    }

    public Member getMemberById(int memberId) throws InvalidInputException, DatabaseException {
        if (memberId <= 0) {
            throw new InvalidInputException("Invalid member ID.");
        }
        try {
            return memberDAO.getMemberById(memberId);
        } catch (SQLException e) {
            throw new DatabaseException("Error retrieving member by ID from the database.", e);
        }
    }
}