package com.services;

import com.DAO.MemberDAO;
import com.models.Member;

public class MemberService {
    private final MemberDAO dao = new MemberDAO();

    public boolean addMember(String name, String email, int mobile, char gender, String address) {
        
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Name cannot be empty.");
            return false;
        }
        if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            System.out.println("Invalid email format.");
            return false;
        }

        if (String.valueOf(mobile).length() != 10) {
            System.out.println("Mobile number must be 10 digits.");
            return false;
        }

        if (gender != 'M' && gender != 'F') {
            System.out.println("Gender must be 'M' or 'F'.");
            return false;
        }

        if (address == null || address.trim().isEmpty()) {
            System.out.println("Address cannot be empty.");
            return false;
        }

        Member member = new Member(0, name, email, mobile, gender, address);
        
        try {
            return dao.save(member);
        } catch (Exception e) {
            System.out.println("Failed to add member: " + e.getMessage());
            return false;
        }
    }

}
