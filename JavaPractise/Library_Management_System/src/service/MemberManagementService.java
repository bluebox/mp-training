package service;

import java.util.List;
import dao.MemberManagementDao;
import domain.Member;

public class MemberManagementService implements MemberManagementServiceInterface{
private MemberManagementDao memberDao;
	public MemberManagementService()
	{
		this.memberDao=new MemberManagementDao();
	}
	public boolean registerMember(Member member) throws Exception {
	    try {
	        return memberDao.registerNewMember(member);
	    } catch (Exception ex) {
	    	String message = ex.getMessage().toLowerCase();
	        if (message.contains("email already exists")) {
	            throw new IllegalArgumentException("Email already exists. Please use a different email.");
	        } else if (message.contains("mobile number already exists")) {
	            throw new IllegalArgumentException("Mobile number already exists. Please use a different number.");
	        } else if (message.contains("duplicate entry")) {
	            throw new IllegalArgumentException("Email or Mobile number already exists.");
	        } else {
	            throw ex;
	        }
	    }
	}
	public boolean updateMember(int memberId, Member member) throws Exception {
	    try {
	        return memberDao.updatermemberDetails(memberId, member);
	    } catch (Exception ex) {
	        String message = ex.getMessage().toLowerCase();
	        if (message.contains("email already exists")) {
	            throw new IllegalArgumentException("Email already exists. Please use a different email.");
	        } else if (message.contains("mobile number already exists")) {
	            throw new IllegalArgumentException("Mobile number already exists. Please use a different number.");
	        } else if (message.contains("duplicate entry")) {
	            throw new IllegalArgumentException("Email or Mobile number already exists.");
	        } else {
	            throw ex;
	        }
	    }
	}
	public List<Member> getAllMembers() {
        try {
            return memberDao.viewAllMembers();
        } catch (Exception e) {
            System.err.println("Error while retrieving members: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }	
}