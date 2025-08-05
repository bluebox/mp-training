package com.library.service.impl;

import com.library.dao.impl.MemberDaoImplementation;
import com.library.dao.interfaces.MemberDao;
import com.library.model.Member;
import com.library.service.interfaces.MemberService;
import com.library.util.DBConnection;

import java.sql.Connection;
import java.util.List;

public class MemberServiceImplementation implements MemberService {

    private final MemberDao dao = new MemberDaoImplementation();
   

    @Override
    public boolean registerMember(Member member) throws Exception {
        validateMember(member); 
        

        Connection conn = null;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            boolean result = dao.addMember(member, conn);
            conn.commit();
            return result;
        } catch (Exception e) {
            if (conn != null) conn.rollback();
            throw new Exception("Failed to register member: " + e.getMessage(), e);
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    @Override
    public boolean modifyMember(Member member) throws Exception {
        

        validateMember(member);

        Connection conn = null;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false);

            boolean result = dao.updateMember(member, conn);
            conn.commit();
            return result;
        } catch (Exception e) {
            if (conn != null) conn.rollback();
            throw new Exception("Failed to update member: " + e.getMessage(), e);
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
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

    public boolean doesMemberExist(String email, long phone) throws Exception {
        List<Member> members = fetchAllMembers();  // or query directly
        return members.stream().anyMatch(m ->
            m.getEmail().equalsIgnoreCase(email) || m.getMobile() == phone
        );
    }

    public boolean memberExists(int memberId) {
        return dao.getMemberById(memberId) != null;
    }
}
