package com.Service;

import java.sql.SQLException;
import java.util.List;

import com.Dao.MemberDao;
import com.Exception.*;
//import exception.InvalidInputException;
import com.Models.Member;

public class MemberService {
	private final MemberDao memberDAO = new MemberDao();

    public void addMember(Member member) throws InvalidInputException, DatabaseException, SQLException {
        validateMember(member);
        memberDAO.addMember(member);
    }

    public void updateMember(Member member) throws InvalidInputException, DatabaseException, SQLException {
        if (member.getMemberId() <= 0) throw new InvalidInputException("Invalid member ID");
        validateMember(member);
        memberDAO.updateMember(member);
    }

    public List<Member> getAllMembers() throws DatabaseException, SQLException {
        return memberDAO.getAllMembers();
    }

    private void validateMember(Member member) throws InvalidInputException {
        if (member.getName() == null || member.getName().trim().isEmpty())
            throw new InvalidInputException("Member name cannot be empty");
        if (member.getEmail() == null || member.getEmail().trim().isEmpty())
            throw new InvalidInputException("Member email cannot be empty");
        if (member.getAddress() == null || member.getAddress().trim().isEmpty())
            throw new InvalidInputException("Member address cannot be empty");
    }

}
