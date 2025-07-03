package com.library.services;

import java.sql.SQLException;
import java.util.List;

import com.library.dao.MemberDAO;
import com.library.domain.Member;
import com.library.serviceInterface.MemberServiceInterface;


public class MemberService implements MemberServiceInterface{
    MemberDAO memberDAO = new MemberDAO();
    
    @Override
    public boolean updateMember(Member member) {
    	if(memberDAO.updateMember(member)) {
    		//update memberlogs
    		return true;
    	}
        return false;
    }
	@Override
	public List<Member> fetchmembers() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addMember(Member member) {
		// TODO Auto-generated method stub
		
	}
}